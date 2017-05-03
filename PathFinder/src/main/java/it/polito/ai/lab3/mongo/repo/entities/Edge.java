package it.polito.ai.lab3.mongo.repo.entities;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "edges")
public class Edge {
	//TODO LA classe Edge serve?
	
	@Id
	private ObjectId id;
	
	//TODO Scegliere quale campo indicizzare
	private String idSource;
	private String idDestination;
	private boolean mode;
	@Indexed
	private int cost;
}
