package it.polito.ai.lab3.services.routing;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.polito.ai.lab3.repo.BusStopsGeoRepository;
import it.polito.ai.lab3.repo.entities.BusStopGeographic;

@Service
@Transactional
public class RoutingServiceImpl implements RoutingService {
	@Autowired
	private BusStopsGeoRepository busStopsGeoRepository;
	

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
		
		// TODO
		
		
		return new PathImpl();
	}
}
