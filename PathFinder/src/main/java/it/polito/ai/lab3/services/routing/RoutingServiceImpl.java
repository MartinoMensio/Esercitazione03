package it.polito.ai.lab3.services.routing;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.polito.ai.lab3.mongo.repo.MinPathsRepository;
import it.polito.ai.lab3.mongo.repo.entities.MinPath;
import it.polito.ai.lab3.repo.BusStopsGeoRepository;
import it.polito.ai.lab3.repo.entities.BusStopGeographic;

@Service
@Transactional
public class RoutingServiceImpl implements RoutingService {
	@Autowired
	private BusStopsGeoRepository busStopsGeoRepository;
	
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
		//List<MinPath> minPaths;
		/*for (BusStopGeographic idSource : stopsNearSrc) {
			for (BusStopGeographic idDestination : stopsNearDst) {
				List<MinPath> minPaths2 = minPathsRepository.myCustomFind(idSource.getId(), idDestination.getId());
				System.out.println("size: " + minPaths2.size());
			}
		}*/
		
		
		
		//List<MinPath> minPaths = minPathsRepository.myCustomFind(idSource, idDestination);
		
		// TODO
		
		
		return new PathImpl();
	}
}
