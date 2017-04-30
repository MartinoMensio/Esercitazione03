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
		Map<String, Map<String, List<Integer>>> busLinesStops = dbReader.getBusLinesStops();

		System.out.println("Reading edges from database (should take around 1 minute)...");
		for (Node node : busStops) {
			Set<Edge> byBus = dbReader.getReachableStopsByBus(node);
			Set<Edge> byWalk = dbReader.getReachableStopsByWalk(node);

			for (Edge edge : byBus) {
				Map<String, List<Integer>> busLineStops = busLinesStops.get(edge.getLineId());
				// search the interesting sub-sequences
				List<Integer> sourceStopSequences = busLineStops.get(edge.getIdSource());
				List<Integer> destinationStopSequences = busLineStops.get(edge.getIdDestination());
				double minCost = Double.MAX_VALUE;
				for (int sourceSequenceNumber : sourceStopSequences) {
					OptionalInt destination = destinationStopSequences.stream().mapToInt(a->a).sorted().filter(a->a>sourceSequenceNumber).findFirst();
					if(destination.isPresent()) {
						int destinationSequenceNumber = destination.getAsInt();
						// get the cost of this candidate sub-sequence
						double cost = dbReader.getSequenceCost(edge.getLineId(), sourceSequenceNumber, destinationSequenceNumber);
						if (cost < minCost) {
							// this is the shortest
							minCost = cost;
						}
					}
				}
				edge.setCost((int)minCost);
				System.out.println("found cost between " + edge.getIdSource() + " and " + edge.getIdDestination() + " : " + edge.getCost());
			}

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
