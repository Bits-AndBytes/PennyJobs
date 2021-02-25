package ca.sheridancollege.pennyjobs.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class JobController {
	
	@GetMapping("/")
	public String loadRoot() {
		return "WelcomePage.html";
	}
	
}
