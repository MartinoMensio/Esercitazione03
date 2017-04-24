package it.polito.ai.lab3;

import java.nio.file.*;
import java.sql.*;
import java.util.*;

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

	private Connection connection;

	private String getBusStops = "select id,ST_Y(location::geometry) as lng, ST_X(location::geometry) as lat from busStopGeographic";
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
	public Set<String> getBusStops() {
		// TODO use proper type
		Set<String> result = new HashSet<String>();
		try {
			ResultSet rs = getBusStopsStmt.executeQuery();
			try {
				while (rs.next()) {
					String id = rs.getString("id");
					Double lat = rs.getDouble("lat");
					Double lng = rs.getDouble("lng");
					// TODO build object of proper type;
					result.add(id + ":" + lat + "," + lng);
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
	public Set<String> getReachableStopsByWalk(double sourceLat, double sourceLng) {
		// TODO use proper type
		Set<String> result = new HashSet<String>();
		try {
			// TODO get source position
			String position = "SRID=4326;POINT(" + sourceLat + " " + sourceLng + ")";
			getNearbyStopsStmt.setString(1, position);
			getNearbyStopsStmt.setString(2, position);
			ResultSet rs = getNearbyStopsStmt.executeQuery();
			try {
				while (rs.next()) {
					String id = rs.getString("id");
					Double distance = rs.getDouble("distance");
					// TODO build object of proper type
					result.add(id + ":" + distance);
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
	public Set<String> getReachableStopsByBus(String sourceId, double sourceLat, double sourceLng) {
		// TODO use proper type
		Set<String> result = new HashSet<String>();
		try {
			// TODO get source position
			String position = "SRID=4326;POINT(" + sourceLat + " " + sourceLng + ")";
			getReachableStopsStmt.setString(1, position);
			getReachableStopsStmt.setString(2, sourceId);
			ResultSet rs = getReachableStopsStmt.executeQuery();
			try {
				while (rs.next()) {
					String id = rs.getString("id");
					Double distance = rs.getDouble("distance");
					// TODO build object of proper type
					result.add(id + ":" + distance);
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
