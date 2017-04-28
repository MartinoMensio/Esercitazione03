package it.polito.ai.lab3;

import java.util.*;

import org.bson.Document;

import com.mongodb.*;
import com.mongodb.client.*;

import it.polito.ai.lab3.mongoClasses.Edge;
import it.polito.ai.lab3.mongoClasses.MinPath;

public class MongoWriter {

	private MongoClient mongoClient;
	private MongoCollection<Document> minPathsCollection;

	/**
	 * sets up connection to mongoDB, DELETING EVERYTHING in the min_paths
	 * collection
	 */
	public MongoWriter() {
		mongoClient = new MongoClient("localhost");
		MongoDatabase database = mongoClient.getDatabase("ai");
		minPathsCollection = database.getCollection("min_paths");
		// clear before starting
		minPathsCollection.drop();
	}

	public void addMinPath(MinPath minPath) {
		List<Document> edges = new ArrayList<Document>();

		for (Edge edge : minPath.getEdges()) {
			edges.add(new Document("idSource", edge.getIdSource()).append("idDestination", edge.getIdDestination())
					.append("mode", edge.isMode()).append("cost", edge.getCost()));
		}

		Document document = new Document("idSource", minPath.getIdSource())
				.append("idDestination", minPath.getIdDestination()).append("edges", edges)
				.append("totalCost", minPath.getTotalCost());

		minPathsCollection.insertOne(document);
	}

	public void close() {
		mongoClient.close();
	}
}
