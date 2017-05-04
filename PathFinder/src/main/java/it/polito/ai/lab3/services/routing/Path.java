package it.polito.ai.lab3.services.routing;

import java.util.List;

public interface Path {

	/**
	 * Returns the list of all the bus stops involved in the computed path in GeoJson format
	 * @return a GeoJson formatted string
	 */
	public String getAllBusStopsAsGeoJson();
	
	/**
	 * Returns the set of line segments of the path. Each segment connects two following bus stops.
	 * Each segment is marked as:
	 * <ul>
	 * 		<li><span style="font-weight: bold;">Bus</span>: if the segment has to be travelled by bus</li>
	 * 		<li><span style="font-weight: bold;">On foot</span>: if the segment has to be travelled on foot</li>
	 * </ul>
	 * @return a GeoJson formatted string
	 */
	public String getPathSegmentsAsGeoJson();
	
	public List<PathSegment> getPathSegments();
}
