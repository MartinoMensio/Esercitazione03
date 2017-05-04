package it.polito.ai.lab3.services.routing;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.polito.ai.lab3.mongo.repo.MinPathsRepository;
import it.polito.ai.lab3.mongo.repo.entities.Edge;
import it.polito.ai.lab3.mongo.repo.entities.MinPath;
import it.polito.ai.lab3.repo.BusStopsGeoRepository;
import it.polito.ai.lab3.repo.BusStopsRepository;
import it.polito.ai.lab3.repo.entities.BusStop;
import it.polito.ai.lab3.repo.entities.BusStopGeographic;

@Service
@Transactional
public class RoutingServiceImpl implements RoutingService {
	@Autowired
	private BusStopsGeoRepository busStopsGeoRepository;
	
	@Autowired
	private BusStopsRepository busStopsRepository;
	
	@Autowired
	private MinPathsRepository minPathsRepository;
	

	public Path getPath(String srcLat, String srcLng, String dstLat, String dstLng) {
		String srcPosition = "SRID=4326;POINT(" + srcLat + " " + srcLng + ")";
		String dstPosition = "SRID=4326;POINT(" + dstLat + " " + dstLng + ")";
		
		List<BusStopGeographic> stopsNearSrc = busStopsGeoRepository.findByDistance(srcPosition, 250.0);
		System.out.println("Fermate vicino punto partenza:");
		for (BusStopGeographic b : stopsNearSrc) {
			System.out.println(b.getId() + " - " + b.getName());
		}
		
		List<BusStopGeographic> stopsNearDst = busStopsGeoRepository.findByDistance(dstPosition, 250.0);
		System.out.println("Fermate vicino punto arrivo:");
		for (BusStopGeographic b : stopsNearDst) {
			System.out.println(b.getId() + " - " + b.getName());
		}
		
		// Prendo tutti i persocrsi minimi tra tutte le source e  tutte le destination
		List<MinPath> minPaths = new ArrayList<MinPath>();
		System.out.println("Calcolo del percorso più veloce:");
		for (BusStopGeographic idSource : stopsNearSrc) {
			for (BusStopGeographic idDestination : stopsNearDst) {
				List<MinPath> minPaths2 = minPathsRepository.myCustomFind(idSource.getId(), idDestination.getId());
				minPaths.addAll(minPaths2);
			}
		}
	
		System.out.println("found paths " + minPaths.size());
		
		// find the minimum
		MinPath bestPath = null;
		for (MinPath p : minPaths) {
			if (bestPath == null || p.getTotalCost() < bestPath.getTotalCost()) {
				bestPath = p;
			}
		}
		if (bestPath == null) {
			// no path found
			return null;
		}
		System.out.println("Best: " + bestPath.getIdSource() + " --> " + bestPath.getIdDestination() + " : " + bestPath.getTotalCost());
		
		List<PathSegment> segments = new ArrayList<PathSegment>();
		for (Edge e : bestPath.getEdges()) {
			
			BusStop source = busStopsRepository.findOne(e.getIdSource());
			BusStop destination = busStopsRepository.findOne(e.getIdDestination());
			List<BusStop> intermediateStops = new ArrayList<BusStop>();
			// TODO get the intermediate stops (not yet stored in mongo
			PathSegmentImpl segment = new PathSegmentImpl(source, destination, e.getLineId(), intermediateStops);
			segments.add(segment);
		}
		Point src = new Point(Double.parseDouble(srcLat), Double.parseDouble(srcLng));
		Point dst = new Point(Double.parseDouble(dstLat), Double.parseDouble(dstLng));
		
		return new PathImpl(src, dst, segments);
	}
}
