package it.polito.ai.lab3.mongo.repo.entities;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "edges")
public class Edge {
	
	@Id
	private ObjectId id;
	
	private String idSource;
	private String idDestination;
	private boolean mode;
	private int cost;
}
