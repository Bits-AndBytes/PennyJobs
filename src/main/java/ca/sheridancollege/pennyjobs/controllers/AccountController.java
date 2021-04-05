package ca.sheridancollege.pennyjobs.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
import ca.sheridancollege.pennyjobs.repositories.RoleRepository;
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
	
	@Autowired
	private RoleRepository roleRepo;
	
	
	//create method to encrypt and SALT passwords to be stored in the database
	public static String encrypt(String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(password);
	}
	
	@GetMapping("/login")
	public String loadLoginPage() {
		return "login.html"; //needs to be created for a custom login page
	}
	
	@GetMapping("/signup")
	public String loadSignUp(Model model, @ModelAttribute Account account) {
		model.addAttribute("account", new Account());
		
		return "signUpPage.html"; //needs to be created
	}
	
	@PostMapping("/signup")
	public String createAccount(@ModelAttribute Account account, 
			@RequestParam String accountType, Model model) {
		
		//take given password and re-enter it as encrypted
		account.setPassword(encrypt(account.getPassword()));
		
		if (accountType.equals("Parent")) {
			Parent p = new Parent();
			account.setAccountType("P");
			account.setParent(p);
			account.getRoles().add(roleRepo.findByRolename("ROLE_PARENT"));
		} 
		
		else if (accountType.equals("Student")){
			Student s = new Student();
			account.setAccountType("S");
			account.setStudent(s);
			account.getRoles().add(roleRepo.findByRolename("ROLE_STUDENT"));
		}
		
		else if (accountType.equals("Poster")){
			JobPoster j = new JobPoster();
			account.setAccountType("J");
			account.setPoster(j);
			account.getRoles().add(roleRepo.findByRolename("ROLE_POSTER"));
		}
		
		accountRepo.save(account);
		
		return "login.html"; //leads to user homepage
	}
	
	//this is not meant to be seen by the user
	//this will be the default mapping to be sent to once user has been validated
	@GetMapping("/accountredirectpage")
	public String accountRedirect(Authentication auth, Model model) {
		
		String destination = "";//default root page
		
		//create an arraylist to store user's roles in
		ArrayList<String> roles = new ArrayList<String>();
		
		//get roles user is validated for
		for (GrantedAuthority g : auth.getAuthorities()) {
			roles.add(g.getAuthority());
		}
		
		//get first role and determine where to send the user
		switch (roles.get(0)) {
		case "ROLE_STUDENT":
			destination = "student"; //home page for student
			break;
			
		case "ROLE_PARENT":
			destination = "parent"; //home page for parent
			break;
			
		case "ROLE_POSTER":
			destination = "poster"; //home page for job poster
			break;
		case "ROLE_ADMIN":
			destination = "admin"; //home page for admin
			break;
		}
		
		return "redirect:/" + destination; //leads to user homepage
	}
	
	//redirects user to this page when they are trying to
	@GetMapping("/access-denied")
	public String accessDenied() {
		return "accessdenied.html";
	}
	
	@GetMapping("/student")
	public String loadStudent() {
		return "student.html";
	}
	
	@GetMapping("/parent")
	public String loadParent() {
		return "parent.html";
	}
	
	@GetMapping("/poster")
	public String loadPoster(){
		
		return "poster.html";
	}
	
	@GetMapping("/admin")
	public String loadAdmin() {
		return "admin.html";
	}
}
