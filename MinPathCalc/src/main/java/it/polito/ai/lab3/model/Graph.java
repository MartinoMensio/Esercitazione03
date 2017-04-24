package it.polito.ai.lab3.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
	private static Map<String,Node> myNodes;
	private static Map<Node, Set<Edge>> myAdjList;

	public Graph() {
		myAdjList = new HashMap<Node, Set<Edge>>();
		myNodes = new HashMap<String, Node>();
		myNumNodes = 0;
		myNumEdges = 0;
	}

	public void addNode (Node myNode) {
		String myNodeName = myNode.getName();
		myNodes.put(myNodeName, myNode);
		myAdjList.put(myNode, new HashSet<Edge>());
		myNumNodes += 1; 
	}

	public void addNode (Node myNode, Set<Edge> myEdgeList) {
		String myNodeName = myNode.getName();
		myNodes.put(myNodeName, myNode);
		myAdjList.put(myNode, myEdgeList);
		myNumNodes += 1; 
		myNumEdges += myEdgeList.size();

	}

	// Aggiunge la lista di edge ad un nodo esistente
	public void addEdges (String name, Set<Edge> myEdgeList) {
		Node myNode = myNodes.get(name);
		if(myNode != null){
			myAdjList.put(myNode, myEdgeList);
			myNumEdges += myEdgeList.size();
		}
	}

	public int getMyNumEdges() {
		return myNumEdges;
	}

	public void setMyNumEdges(int myNumEdges) {
		this.myNumEdges = myNumEdges;
	}

	public int getMyNumNodes() {
		return myNumNodes;
	}

	public void setMyNumNodes(int myNumNodes) {
		this.myNumNodes = myNumNodes;
	}

}
