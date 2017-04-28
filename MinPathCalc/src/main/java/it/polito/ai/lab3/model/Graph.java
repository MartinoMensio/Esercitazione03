package it.polito.ai.lab3.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.*;

import it.polito.ai.lab3.mongoClasses.Edge;

/**
 * Questa classe rappresenta il grafo direzionale all'interno del quale 
 * i nodi(Node.class) rappresentano le fermate mentre
 * gli archi(Edge.class) rappresentano il costo fra un nodo e un successivo.
 * Malnati consiglia di vedere il grafo come una lista di adiacenze dove
 * per adiacenza si intende la distanza che c'è fra uno stop e il successivo in una fermata */

public class Graph {
	private int myNumNodes;
	private int myNumEdges;
	private static ConcurrentMap<String, Node> myNodes;
	private static ConcurrentMap<String, Set<Edge>> myAdjList;

	public Graph() {
		myAdjList = new ConcurrentHashMap<String, Set<Edge>>();
		myNodes = new ConcurrentHashMap<String, Node>();
		myNumNodes = 0;
		myNumEdges = 0;
	}

	public void addNode (Node myNode) {
		String myNodeId = myNode.getId();
		myNodes.put(myNodeId, myNode);
		myAdjList.put(myNodeId, new HashSet<Edge>());
		myNumNodes += 1; 
	}

	public void addNode (Node myNode, Set<Edge> myEdgeList) {
		String myNodeId = myNode.getId();
		myNodes.put(myNodeId, myNode);
		myAdjList.put(myNodeId, myEdgeList);
		myNumNodes += 1; 
		myNumEdges += myEdgeList.size();

	}

	// Aggiunge la lista di edge ad un nodo esistente
	public void addEdges (String sourceId, Set<Edge> myEdgeList) {
		Node myNode = myNodes.get(sourceId);
		if(myNode != null){
			myAdjList.get(sourceId).addAll(myEdgeList);
			myNumEdges += myEdgeList.size();
		}
	}

	public int getMyNumEdges() {
		return myNumEdges;
	}

	public int getMyNumNodes() {
		return myNumNodes;
	}

	public Set<String> getNodes() {
		return myNodes.keySet();
	}
	
	public Set<Edge> getEdges(String sourceId) {
		return myAdjList.get(sourceId);
	}

}
