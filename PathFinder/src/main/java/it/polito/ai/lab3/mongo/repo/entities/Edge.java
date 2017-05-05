package it.polito.ai.lab3.mongo.repo.entities;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class Edge {
	@Id
	private ObjectId id;
	private String idSource;
	private String idDestination;
	private boolean mode;
	private int cost;
	private String lineId;
	
	private List<String> stopsId;
	
	public List<String> getStopsId() {
		return stopsId;
	}
	public void setStopsId(List<String> stopsId) {
		this.stopsId = stopsId;
	}
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public String getIdSource() {
		return idSource;
	}
	public void setIdSource(String idSource) {
		this.idSource = idSource;
	}
	public String getIdDestination() {
		return idDestination;
	}
	public void setIdDestination(String idDestination) {
		this.idDestination = idDestination;
	}
	public boolean isMode() {
		return mode;
	}
	public void setMode(boolean mode) {
		this.mode = mode;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public String getLineId() {
		return lineId;
	}
	public void setLineId(String lineId) {
		this.lineId = lineId;
	}
	
}
