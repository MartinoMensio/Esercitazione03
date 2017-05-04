package it.polito.ai.lab3.services.routing;

import java.util.List;


public interface Path {
	
	/**
	 * The user-defined source point clicked on the map
	 * @return
	 */
	public Point getSource();
	
	/**
	 * The user-defined destination point clicked on the map
	 * @return
	 */
	public Point getDestination();

	/**
	 * Returns the list of all the bus stops involved in the computed path in GeoJson format
	 * @return a GeoJson formatted string
	 */
	@Deprecated
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
	@Deprecated
	public String getPathSegmentsAsGeoJson();
	
	/**
	 * Returns the list of all the segments that compose the whole path.
	 * Each segment represents a path that is traveled either on foot or by bus/metro.
	 * 
	 * @return list of PathSegment object
	 */
	public List<PathSegment> getPathSegments();
}
