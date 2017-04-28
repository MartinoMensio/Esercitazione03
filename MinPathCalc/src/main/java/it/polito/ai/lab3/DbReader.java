package it.polito.ai.lab3;

import java.nio.file.*;
import java.sql.*;
import java.util.*;

import it.polito.ai.lab3.mongoClasses.Edge;
import it.polito.ai.lab3.model.Node;

/*

- lista fermate con posizione "select id,ST_X(location::geometry) as lng, ST_X(location::geography) as lat from busStopGeographic"

- lista e distanza fermate raggiungibili con mezzo 
"select id, ST_Distance(location, MY_LOCATION) as distance from BusStopGeographic
where id in (
  // l'id è uno di quelli raggiungibili direttamente con un bus che passa per la nostra fermata
  select a.stopId from BusLineStop a, BusLineStop b
  where a.lineId=b.lineId
  and b.stopId=MY_STOP
)"

- distanza di fermate raggiungibili a piedi
"select id, ST_Distance(location, MY_LOCATION) as distance
from busStopGeographic
where ST_Distance(location, MY_LOCATION) < 250"


 */
public class DbReader {
	
	private static int WALK_WEIGHT = 10;
	private static int BUS_WEIGHT = 1;

	private Connection connection;

	private String getBusStops = "select id, name, ST_Y(location::geometry) as lng, ST_X(location::geometry) as lat from busStopGeographic";
	private String getReachableStops = "select id, ST_Distance(location, ST_GeographyFromText(?)) as distance from BusStopGeographic where id in (select a.stopId from BusLineStop a, BusLineStop b where a.lineId=b.lineId and b.stopId=?)";
	private String getNearbyStops = "select id, ST_Distance(location, ST_GeographyFromText(?)) as distance from busStopGeographic where ST_Distance(location, ST_GeographyFromText(?)) < 250";

	private PreparedStatement getBusStopsStmt;
	private PreparedStatement getReachableStopsStmt;
	private PreparedStatement getNearbyStopsStmt;

	public DbReader() {
		try {
			Class.forName("org.postgresql.Driver");

			String server = null;
			try {
				List<String> lines = Files.readAllLines(Paths
						.get(DbReader.class.getClassLoader().getResource("db_ip.txt").toURI().toString().substring(6)));
				server = lines.get(0);
			} catch (Exception e) {
				server = "localhost";
			}

			connection = DriverManager.getConnection("jdbc:postgresql://" + server + ":5432/trasporti", "postgres",
					"ai-user-password");
			// read-only, so don't need transactions
			getBusStopsStmt = connection.prepareStatement(getBusStops);
			getReachableStopsStmt = connection.prepareStatement(getReachableStops);
			getNearbyStopsStmt = connection.prepareStatement(getNearbyStops);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * Get the list of bus stops with their position
	 * 
	 * @return
	 */
	public Set<Node> getBusStops() {
		Set<Node> result = new HashSet<Node>();
		try {
			ResultSet rs = getBusStopsStmt.executeQuery();
			try {
				while (rs.next()) {
					String id = rs.getString("id");
					String name = rs.getString("name");
					Double lat = rs.getDouble("lat");
					Double lng = rs.getDouble("lng");
					result.add(new Node(id, name, lat, lng));
				}
			} finally {
				rs.close();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	/**
	 * Get the list of reachable stops in proximity of the source (250m)
	 * 
	 * @param source
	 * @return
	 */
	public Set<Edge> getReachableStopsByWalk(Node srcNode) {
		Set<Edge> result = new HashSet<Edge>();
		try {
			String position = "SRID=4326;POINT(" + srcNode.getLat() + " " + srcNode.getLng() + ")";
			getNearbyStopsStmt.setString(1, position);
			getNearbyStopsStmt.setString(2, position);
			ResultSet rs = getNearbyStopsStmt.executeQuery();
			try {
				while (rs.next()) {
					String id = rs.getString("id");
					double distance = rs.getDouble("distance");
					result.add(new Edge(srcNode.getId(), id,true, (int)distance * WALK_WEIGHT));
				}
			} finally {
				rs.close();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return result;
	}

	/**
	 * Get the list of reachable stops using a single run on a bus
	 * 
	 * @param source
	 * @return
	 */
	public Set<Edge> getReachableStopsByBus(Node srcNode) {
		Set<Edge> result = new HashSet<Edge>();
		try {
			String position = "SRID=4326;POINT(" + srcNode.getLat() + " " + srcNode.getLng() + ")";
			getReachableStopsStmt.setString(1, position);
			getReachableStopsStmt.setString(2, srcNode.getId());
			ResultSet rs = getReachableStopsStmt.executeQuery();
			try {
				while (rs.next()) {
					String id = rs.getString("id");
					double distance = rs.getDouble("distance");
					result.add(new Edge(srcNode.getId(), id, false, (int)distance * BUS_WEIGHT));
				}
			} finally {
				rs.close();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return result;
	}
}
