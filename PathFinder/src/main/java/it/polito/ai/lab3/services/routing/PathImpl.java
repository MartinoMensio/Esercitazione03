package it.polito.ai.lab3.services.routing;

import java.util.List;

import it.polito.ai.lab3.repo.entities.BusStop;

public class PathImpl implements Path {
	private BusStop source;
	private BusStop destination;
	private List<PathSegment> pathSegments;
	
	
	public BusStop getSource() {
		return source;
	}

	public BusStop getDestination() {
		return destination;
	}

	public String getAllBusStopsAsGeoJson() {
		// TODO Auto-generated method stub
		return "{\"features\":[{\"geometry\":{\"coordinates\":[7.60401,45.03206],\"type\":\"Point\"},\"type\":\"Feature\",\"properties\":{\"busStopName\":\"CIMITERO PARCO CAP.\",\"busStopId\":\"3191\",\"lines\":[\"102\"]}},{\"geometry\":{\"coordinates\":[7.60522,45.03397],\"type\":\"Point\"},\"type\":\"Feature\",\"properties\":{\"busStopName\":\"BERTANI\",\"busStopId\":\"348\",\"lines\":[\"74\",\"102\",\"5B\"]}},{\"geometry\":{\"coordinates\":[7.60175,45.03557],\"type\":\"Point\"},\"type\":\"Feature\",\"properties\":{\"busStopName\":\"CAMPO 21\",\"busStopId\":\"3201\",\"lines\":[\"102\"]}},{\"geometry\":{\"coordinates\":[7.59969,45.03575],\"type\":\"Point\"},\"type\":\"Feature\",\"properties\":{\"busStopName\":\"CAMPO 26\",\"busStopId\":\"3202\",\"lines\":[\"102\"]}},{\"geometry\":{\"coordinates\":[7.59692,45.03697],\"type\":\"Point\"},\"type\":\"Feature\",\"properties\":{\"busStopName\":\"CAMPO 38\",\"busStopId\":\"3203\",\"lines\":[\"102\"]}},{\"geometry\":{\"coordinates\":[7.5945,45.03689],\"type\":\"Point\"},\"type\":\"Feature\",\"properties\":{\"busStopName\":\"CAMPO 47\",\"busStopId\":\"3204\",\"lines\":[\"102\"]}},{\"geometry\":{\"coordinates\":[7.5945,45.03689],\"type\":\"Point\"},\"type\":\"Feature\",\"properties\":{\"busStopName\":\"CAMPO 47\",\"busStopId\":\"3204\",\"lines\":[\"102\"]}},{\"geometry\":{\"coordinates\":[7.59541,45.03582],\"type\":\"Point\"},\"type\":\"Feature\",\"properties\":{\"busStopName\":\"CAMPO DELLA MEMORIA\",\"busStopId\":\"3205\",\"lines\":[\"102\"]}},{\"geometry\":{\"coordinates\":[7.59565,45.0334],\"type\":\"Point\"},\"type\":\"Feature\",\"properties\":{\"busStopName\":\"CAMPO 34\",\"busStopId\":\"3220\",\"lines\":[\"102\"]}},{\"geometry\":{\"coordinates\":[7.59855,45.03303],\"type\":\"Point\"},\"type\":\"Feature\",\"properties\":{\"busStopName\":\"CAMPO 25\",\"busStopId\":\"3221\",\"lines\":[\"102\"]}},{\"geometry\":{\"coordinates\":[7.59888,45.03228],\"type\":\"Point\"},\"type\":\"Feature\",\"properties\":{\"busStopName\":\"CAMPO 11\",\"busStopId\":\"3352\",\"lines\":[\"102\"]}},{\"geometry\":{\"coordinates\":[7.60401,45.03206],\"type\":\"Point\"},\"type\":\"Feature\",\"properties\":{\"busStopName\":\"CIMITERO PARCO CAP.\",\"busStopId\":\"3191\",\"lines\":[\"102\"]}}],\"type\":\"FeatureCollection\"};";
	}

	public String getPathSegmentsAsGeoJson() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<PathSegment> getPathSegments() {
		return pathSegments;
	}
}
