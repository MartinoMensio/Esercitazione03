package it.polito.ai.lab3.services.routing;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

@Service
@Transactional
public class RoutingServiceImpl implements RoutingService {

	public Path getPath(String srcLat, String srcLng, String dstLat, String dstLng) {
		// TODO
		
		return new PathImpl();
	}
}
