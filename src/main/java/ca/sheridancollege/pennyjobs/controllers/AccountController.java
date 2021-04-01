package ca.sheridancollege.pennyjobs.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.pennyjobs.beans.Account;
import ca.sheridancollege.pennyjobs.beans.JobPoster;
import ca.sheridancollege.pennyjobs.beans.Parent;
import ca.sheridancollege.pennyjobs.beans.Student;
import ca.sheridancollege.pennyjobs.repositories.AccountRepository;
import ca.sheridancollege.pennyjobs.repositories.JobPosterRepository;
import ca.sheridancollege.pennyjobs.repositories.ParentRepository;
import ca.sheridancollege.pennyjobs.repositories.StudentRepository;

@Controller
public class AccountController {
	
	@Autowired
	private AccountRepository accountRepo;
	
	@Autowired 
	private StudentRepository studentRepo;
	
	@Autowired 
	private ParentRepository parentRepo;
	
	@Autowired
	private JobPosterRepository posterRepo;

	@GetMapping("/signup")
	public String loadSignUp() {
		
		return "signup.html"; //needs to be created
	}
	
	@GetMapping("/login")
	public String loadLoginPage() {
		
		
		return "login.html";//needs to be created for a custom login page
	}
	
	@PostMapping("/signup")
	public String createAccount(@ModelAttribute Account account, 
			@RequestParam String accountType, Model model) {
		
		if (accountType.equals("Parent")) {
			Parent p = new Parent();
			account.setAccountType("P");
			account.setParent(p);
		} 
		
		else if (accountType.equals("Student")){
			Student s = new Student();
			account.setAccountType("S");
			account.setStudent(s);
		}
		
		else if (accountType.equals("Poster")){
			JobPoster j = new JobPoster();
			account.setAccountType("J");
			account.setPoster(j);
		}
		
		accountRepo.save(account);
		
		return ""; //leads to user homepage
	}
	
	//this is not meant to be seen by the user
	//this will be the default mapping to be sent to once user has been validated
	@GetMapping("/accountpageredirect")
	public String accountRedirect(Authentication auth, Model model) {
		
		String destination = "";//default home page
		
		//create an arraylist to store user's roles in
		ArrayList<String> roles = new ArrayList<String>();
		
		//get roles user is validated for
		for (GrantedAuthority g : auth.getAuthorities()) {
			roles.add(g.getAuthority());
		}
		
		
		//get first role and determine where to send the user
		switch (roles.get(0)) {
		case "ROLE_STUDENT":
			destination = "student.html"; //home page for student
			break;
			
		case "ROLE_PARENT":
			destination = "parent.html"; //home page for parent
			break;
			
		case "ROLE_POSTER":
			destination = "poster.html"; //home page for job poster
			break;
		case "ROLE_ADMIN":
			destination = "admin.html"; //home page for admin
			break;
		}
		
		return destination; //leads to user homepage
	}
}
