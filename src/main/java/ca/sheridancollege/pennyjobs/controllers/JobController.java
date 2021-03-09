package ca.sheridancollege.pennyjobs.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class JobController {
	
	@GetMapping("/")
	public String loadRoot() {
		return "WelcomePage.html";
	}
	
	@GetMapping("/login")
	public String loadLoginPage() {
		return "";
	}
	
	@GetMapping("/signup")
	public String loadSignupPage() {
		return "";
	}
	
	@GetMapping("jobposting")
	public String loadJobPosting() {
		return "JobForm.html";
	}
	
	@GetMapping("joblist")
	public String loadJobList() {
		return "";
	}
}
