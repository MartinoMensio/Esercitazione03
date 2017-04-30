/**
 * This class implements the POST-then-GET paradigm
 * 
 * @author alessio
 */

package it.polito.ai.lab3.web.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.polito.ai.lab3.services.routing.Path;
import it.polito.ai.lab3.services.routing.RoutingService;
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
		
		// Add the results for the view
		ras.addFlashAttribute("path", path.getAllBusStopsAsGeoJson());
		
		/*
		 * Redirect to the resultPath page
		 * Warning! do not put space after ":" otherwise the flash attributes are not passed to the new page
		 */
		return "redirect:resultPath"; 
	}
}
