package ca.sheridancollege.pennyjobs.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ca.sheridancollege.pennyjobs.beans.Job;
import ca.sheridancollege.pennyjobs.repositories.AccountRepository;
import ca.sheridancollege.pennyjobs.repositories.JobPosterRepository;
import ca.sheridancollege.pennyjobs.repositories.JobRepository;
import ca.sheridancollege.pennyjobs.repositories.ParentRepository;
import ca.sheridancollege.pennyjobs.repositories.StudentRepository;

@Controller
public class JobController {
	
	@Autowired
	private JobRepository jRepo;
	
	@Autowired
	private AccountRepository accountRepo;
	
	@Autowired 
	private StudentRepository studentRepo;
	
	@Autowired 
	private ParentRepository parentRepo;
	
	@Autowired
	private JobPosterRepository posterRepo;
	
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
	
	@GetMapping("jobpost")
	public String loadAddJob(Model model, @ModelAttribute Job job) {
		model.addAttribute("job", new Job());
		return "JobForm.html";
	}
	
	@PostMapping("jobpost")
	public String addJob(Model model, @ModelAttribute Job job) {
		jRepo.save(job);
		model.addAttribute("job", new Job());
		model.addAttribute("jobs", jRepo.findAll());
		return "JobList.html";
	}
	
	@GetMapping("joblist")
	public String loadJobList() {
		return "";
	}
}
