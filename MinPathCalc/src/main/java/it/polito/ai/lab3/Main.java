package it.polito.ai.lab3;

import java.util.Set;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DbReader dbReader = new DbReader();
		
		for (String s: dbReader.getBusStops()) {
			System.out.println("bus stop: " + s);
		}
		
		// some stupid testing, to see if it works
		Set<String> result = dbReader.getReachableStopsByWalk(45.0626559,7.6788946);
		System.out.println("got some results for stations near 45.00996,7.66691: " + result.size());
		
		Set<String> result2 = dbReader.getReachableStopsByBus("40", 45.0626559,7.6788946);
		System.out.println("got some results for stations reachable from 40 (porta nuova): " + result2.size());
	}

}
