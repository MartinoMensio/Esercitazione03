package it.polito.ai.lab3.mongo.repo.entities;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "min_paths")
public class MinPath {

	@Id
	private ObjectId id;
	
	//TODO Scegliere quale campo indicizzare
	private String sourceId;
	private String idDestination;
	private List<Document> edges;
	private int totalCost;

}
