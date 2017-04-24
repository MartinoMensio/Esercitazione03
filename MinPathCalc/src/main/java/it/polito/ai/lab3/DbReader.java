package it.polito.ai.lab3;

import java.util.*;

/*

- lista fermate con posizione "select id,ST_AsText(location) from busStopGeographic"

- lista e distanza fermate raggiungibili con mezzo 
"select id, ST_Distance(location, MY_LOCATION) from BusStopGeographic
where id in (
  // l'id è uno di quelli raggiungibili direttamente con un bus che passa per la nostra fermata
  select a.stopId from BusLineStop a, BusLineStop b
  where a.lineId=b.lineId
  and b.stopId=MY_STOP
)"

- distanza di fermate raggiungibili a piedi
"select id, ST_Distance(location, MY_LOCATION)
from busStopGeographic
where ST_Distance(location, MY_LOCATION) < 250"


 */
public class DbReader {
	
	public DbReader() {
		// TODO
	}
	
	/**
	 * Get the list of bus stops with their position
	 * @return
	 */
	public List<Object> getBusStops() {
		// TODO
		return null;
	}

	/**
	 * Get the list of reachable stops in proximity of the source (250m)
	 * @param source
	 * @return
	 */
	public List<Object> getReachableStopsByWalk(Object source) {
		// TODO
		return null;
	}
	
	/**
	 * Get the list of reachable stops using a single run on a bus
	 * @param source
	 * @return
	 */
	public List<Object> getReachableStopsByBus(Object source) {
		// TODO
		return null;
	}
}
