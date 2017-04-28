package it.polito.ai.lab3;

import java.util.Map;
import java.util.Set;

import it.polito.ai.lab3.model.*;
import it.polito.ai.lab3.mongoClasses.MinPath;
import it.polito.ai.lab3.mongoClasses.Edge;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DbReader dbReader = new DbReader();
		
		Graph graph = new Graph();
		
		System.out.println("Reading stops from database..");
		
		Set<Node> busStops = dbReader.getBusStops();
		
		System.out.println("Reading edges from database (should take around 1 minute)...");
		// single thread implementation
		for (Node node : busStops) {
			Set<Edge> byBus = dbReader.getReachableStopsByBus(node);
			Set<Edge> byWalk = dbReader.getReachableStopsByWalk(node);
			
			graph.addNode(node);
			graph.addEdges(node.getId(), byBus);
			graph.addEdges(node.getId(), byWalk);
		}
		
		System.out.println("The graph contains " + graph.getMyNumNodes() + " nodes and " + graph.getMyNumEdges() + " edges");
		
		Dijsktra dijkstra = new Dijsktra(graph);
		
		System.out.println("Going to run Dijkstra from each node (should take around 15 min)...");
		
		/*
		// SERIAL
		for (String node : graph.getNodes()) {
			System.out.println("Calculating for node " + node);
			// evaluate the minimum path for every stop starting from this node
			Map<String, MinPath> tmp = dijkstra.shortestPath(node);
			
		}
		*/
		
		// PARALLEL
		graph.getNodes().parallelStream().forEach(node -> {
			// evaluate the minimum path for every stop starting from this node
			Map<String, MinPath> tmp = dijkstra.shortestPath(node);
		});
		
		// TODO store the found paths
		
		System.out.println("Done");
	}

}
