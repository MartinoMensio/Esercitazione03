package it.polito.ai.lab3.web.controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping({"/", "/home", "/index"})
public class HomeController {

	@RequestMapping(method = RequestMethod.GET)
	public String showHomePage(ModelMap model) {
		return "home";
	}
}
