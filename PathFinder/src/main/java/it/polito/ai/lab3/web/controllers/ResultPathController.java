package it.polito.ai.lab3.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.polito.ai.lab3.services.routing.Path;
import it.polito.ai.lab3.services.routing.RoutingService;

@Controller
@RequestMapping("/resultPath")
public class ResultPathController {
	private RoutingService routingService;
	
	
	@Autowired
	public ResultPathController(RoutingService routingService) {
		this.routingService = routingService;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String showResult(ModelMap model) {
		//TODO Add info to the model, i.e. the path and other info
		
		Path path = routingService.getPath("", "", "", "");
		model.addAttribute("path", path.getAllBusStopsAsGeoJson());
		
		
		return "resultPath";
	}
}
