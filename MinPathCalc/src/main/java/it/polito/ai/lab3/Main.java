package it.polito.ai.lab3;

import java.util.*;

import it.polito.ai.lab3.model.*;
import it.polito.ai.lab3.mongoClasses.*;

public class Main {

	public static void main(String[] args) {
		DbReader dbReader = new DbReader();
		Graph graph = new Graph();

		System.out.println("Reading stops from database..");
		Set<Node> busStops = dbReader.getBusStops();

		System.out.println("Reading edges from database (should take around 1 minute)...");
		for (Node node : busStops) {
			Set<Edge> byBus = dbReader.getReachableStopsByBus(node);
			Set<Edge> byWalk = dbReader.getReachableStopsByWalk(node);

			graph.addNode(node);
			graph.addEdges(node.getId(), byBus);
			graph.addEdges(node.getId(), byWalk);
		}

		System.out.println(
				"The graph contains " + graph.getMyNumNodes() + " nodes and " + graph.getMyNumEdges() + " edges");

		dbReader.close();
		dbReader = null;
		
		Dijsktra dijkstra = new Dijsktra(graph);

		System.out.println("Going to run Dijkstra from each node (should take around 15 min)...");
		
		MongoWriter mongoWriter = new MongoWriter();

		// work with the data collected
		graph.getNodes()
				// get a parallel stream to improve performances (automatic
				// usage of threads)
				.parallelStream()
				// for each node
				.map(node -> {
					// evaluate the minimum path for every stop starting from
					// this node
					return dijkstra.shortestPath(node);
				})
				// map from a Stream<Map<String,MinPath>> to a Stream<MinPath>
				.flatMap(map -> map.values().stream())
				// for each MinPath
				.forEach(minPath -> {
					// store the path
					mongoWriter.addMinPath(minPath);
				});
		
		mongoWriter.close();

		System.out.println("Done");
	}

}
