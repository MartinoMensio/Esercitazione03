/**
 * This class implements the POST-then-GET paradigm
 * 
 * @author alessio
 */

package it.polito.ai.lab3.web.controllers;

import java.util.List;

import javax.validation.Valid;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.polito.ai.lab3.services.routing.Path;
import it.polito.ai.lab3.services.routing.PathSegment;
import it.polito.ai.lab3.services.routing.Point;
import it.polito.ai.lab3.services.routing.RoutingService;
import it.polito.ai.lab3.web.controllers.util.FeaturesCollection;
import it.polito.ai.lab3.web.forms.SrcDstPointsForm;


@Controller
@RequestMapping("/computePath")
public class ComputePathController {
	private RoutingService routingService;


	@Autowired
	public ComputePathController(RoutingService routingService) {
		this.routingService = routingService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String showResultPage(ModelMap model) {
		// set an empty SrcDstPointsForm object to be used for the POST request
		model.addAttribute("srcDstPoints", new SrcDstPointsForm());
		return "computePath";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String computePage(@Valid @ModelAttribute("srcDstPoints") SrcDstPointsForm srcDstPoints, BindingResult result, RedirectAttributes ras) {
		// validate received data
		if(result.hasErrors()) {
			// If there are errors then show again the previous page
			return "computePath";
		}

		// Compute the path from the source point to the destination point
		Path path = routingService.getPath(srcDstPoints.getSrcLat(), 
				srcDstPoints.getSrcLng(), 
				srcDstPoints.getDstLat(), 
				srcDstPoints.getDstLng());

		// Generate the GeoJson for the src and dst points and add the results to the model
		Point src = path.getSource();		
		Point dst = path.getDestination();
		String srcDstPointsGeoJson = srcDstPointsToGeoJson(src, dst);
		ras.addFlashAttribute("srcDstPoints", srcDstPointsGeoJson);
		
		String pathGeoJson = pathSegmentsToGeoJson(path.getPathSegments());
		ras.addFlashAttribute("path", pathGeoJson);

		/*
		 * Redirect to the resultPath page
		 * Warning! do not put space after ":" otherwise the flash attributes are not passed to the new page
		 */
		return "redirect:resultPath";
	}

	private String srcDstPointsToGeoJson(Point src, Point dst) {
		FeaturesCollection collection = FeaturesCollection.newFeaturesCollection();
		
		// Create the feature for the src point marker
		JSONObject srcPointFeature = createPointFeature(src.getLat(), src.getLng());
		collection.addFeature(srcPointFeature);
		
		// Create the feature for the dst point marker
		JSONObject dstPointFeature = createPointFeature(dst.getLat(), dst.getLng());
		collection.addFeature(dstPointFeature);

		return collection.getGeoJson();
	}

	private String pathSegmentsToGeoJson(List<PathSegment> segments) {
		FeaturesCollection collection = FeaturesCollection.newFeaturesCollection();
		
		JSONArray coordinates = new JSONArray();
		for (PathSegment p: segments) {
			JSONArray segmentStart = new JSONArray();
			segmentStart.put(p.getSource().getLongitude());
			segmentStart.put(p.getSource().getLatitude());
			coordinates.put(segmentStart);
			
			JSONArray segmentEnd = new JSONArray();
			segmentEnd.put(p.getDestination().getLongitude());
			segmentEnd.put(p.getDestination().getLatitude());
			coordinates.put(segmentEnd);
		}
		
		JSONObject geometry = new JSONObject();
		geometry.put("type", "LineString");
		geometry.put("coordinates", coordinates);
		
		// Create and fill up the feature object
		JSONObject feature = new JSONObject();
		feature.put("type", "Feature");
		feature.put("geometry", geometry);
		
		collection.addFeature(feature);
		return collection.getGeoJson();
	}
	
	private JSONObject createPointFeature(Double lat, Double lng) {
		// Create coordinate object and set latitude and longitude
		JSONArray coordinates = new JSONArray();
		coordinates.put(lng);
		coordinates.put(lat);

		// Create and fill up the geometry object with type and coordinates
		JSONObject geometry = new JSONObject();
		geometry.put("type", "Point");
		geometry.put("coordinates", coordinates);

		// TODO ? add the pop up with point info, e.g. stopId, stopName
		// Create and fill up the properties object
		/*JSONObject properties = new JSONObject();
		properties.put("busStopId", stop.getId());
		properties.put("busStopName", stop.getName());*/

		// Create and fill up the feature object
		JSONObject feature = new JSONObject();
		feature.put("type", "Feature");
		feature.put("geometry", geometry);

		return feature;
	}
}
