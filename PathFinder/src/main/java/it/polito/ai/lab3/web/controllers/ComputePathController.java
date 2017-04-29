package it.polito.ai.lab3.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/computePath")
public class ComputePathController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String showResultPage(ModelMap model) {
		return "computePath";
	}
	
	// TODO verificare la possibilità di usare la stessa pagina al posto di result Path
}
