package it.polito.ai.lab3.web.controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import it.polito.ai.lab3.entities.*;

@Controller
@RequestMapping({"/", "/home", "/index"})
public class HomeController {

	TestEntity testEntity;

	@Autowired
	public HomeController(TestEntity testEntity) {
		this.testEntity = testEntity;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String showHomePage(ModelMap model) {
		model.addAttribute("greeting", testEntity.greet());
		model.addAttribute("test", "stringa di test");
		return "home";
	}
}
