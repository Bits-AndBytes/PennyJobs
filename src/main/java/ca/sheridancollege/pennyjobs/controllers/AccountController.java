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

/**
 * This class is for controlling the account functionalities
 * 
 * @author Weiye Chen, Gregory Knott, Patrick Ferdinand Adhitama, Dimitrios Vlachos
 *
 */

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
	
	/**
	 * It is a method to encrypt and SALT passwords to be stored in the database
	 * @param password
	 * @return
	 */
	
	public static String encrypt(String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(password);
	}
	
	/**
	 * It is a method to get the user name base on the Email they assign in
	 * @param auth
	 * @return
	 */
	
	public String getName(Authentication auth) {
		
		Account account = accountRepo.findByEmail(auth.getName());

		return account.getFirstName();
	}
	
	/**
	 * Load the login page
	 * @return
	 */
	
	@GetMapping("/login")
	public String loadLoginPage() {
		return "login.html"; //needs to be created for a custom login page
	}
	
	/**
	 * Load the sign up page for new user
	 * @param model
	 * @param account
	 * @return
	 */
	@GetMapping("/signup")
	public String loadSignUp(Model model, @ModelAttribute Account account) {
		model.addAttribute("account", new Account());
		
		return "SignUpPage.html"; //needs to be created
	}
	
	/**
	 * This method is used to create a new account and based on the account to give
	 * the user a role and store it in database
	 * @param account
	 * @param accountType
	 * @param model
	 * @return
	 */
	@PostMapping("/signup")
	public String createAccount(@ModelAttribute Account account, 
			@RequestParam String accountType, Model model) {
		
		//take given password and re-enter it as encrypted
		account.setPassword(encrypt(account.getPassword()));
		
		if (accountType.equals("Parent")) {
			Parent p = new Parent();
			p.setAccount(account);
			account.setAccountType("P");
			account.setParent(p);
			account.getRoles().add(roleRepo.findByRolename("ROLE_PARENT"));
		} 
		
		else if (accountType.equals("Student")){
			Student s = new Student();
			s.setAccount(account);
			account.setAccountType("S");
			account.setStudent(s);
			account.getRoles().add(roleRepo.findByRolename("ROLE_STUDENT"));
		}
		
		else if (accountType.equals("Poster")){
			JobPoster j = new JobPoster();
			j.setAccount(account);
			account.setAccountType("J");
			account.setPoster(j);
			account.getRoles().add(roleRepo.findByRolename("ROLE_POSTER"));
		}
		
		accountRepo.save(account);
		
		return "login.html"; //leads to user homepage
	}
	
	
	/**
	 * this is not meant to be seen by the user
	 * this will be the default mapping to be sent to once user has been validated
	 * @param auth
	 * @param model
	 * @return
	 */
	
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
	
	/**
	 * redirects user to this page when they are trying to
	 * @return
	 */
	
	@GetMapping("/access-denied")
	public String accessDenied() {
		return "accessdenied.html";
	}
	
	/**
	 * Load the student's page
	 * @param auth
	 * @param model
	 * @return
	 */
	
	@GetMapping("/student")
	public String loadStudent(Authentication auth, Model model) {
		
		if (auth.isAuthenticated()) {
			model.addAttribute("name", getName(auth));
			Account account = accountRepo.findByEmail(auth.getName());
			if (account.getAccountType().equals("S")) {
				Student student = studentRepo.findByAccount(account);
				model.addAttribute("student", student);
			}
		}
		return "student.html";
	}
	
	@GetMapping("/student/profile")
	public String loadStudentProfile(Authentication auth, Model model) {
		
		if (auth.isAuthenticated()) {
			Account account = accountRepo.findByEmail(auth.getName());
			if (account.getAccountType().equals("S")) {
				Student student = studentRepo.findByAccount(account);
				model.addAttribute("student", student);
				model.addAttribute("account", account);
			}
		}
		
		return "studentprofile.html";
	}
	
	@GetMapping("/student/edit")
	public String loadEditStudent(Authentication auth, Model model) {
		
		if (auth.isAuthenticated()) {
			Account account = accountRepo.findByEmail(auth.getName());
			if (account.getAccountType().equals("S")) {
				Student student = studentRepo.findByAccount(account);
				model.addAttribute("account", account);
				model.addAttribute("student", student);
			}
		}
		
		return "editstudent.html";
	}
	
	@PostMapping("/student/modify")
	public String modifyStudent(@RequestParam int studentId, @RequestParam int accountId ,@RequestParam String firstName,
			@RequestParam String lastName, @RequestParam String bio, @RequestParam String transferEmail) {
		//Replace existing student object with updated values
		
		
		Student student = studentRepo.findById(studentId).get();
		Account account = accountRepo.findById(accountId).get();
		
		student.setBio(bio);
		student.setTransferEmail(transferEmail);
		
		studentRepo.save(student);
		
		account.setFirstName(firstName);
		account.setLastName(lastName);
		
		accountRepo.save(account);
		
		return "redirect:/student/profile";
	}
	
	
	@PostMapping("/settransferemail")
	public String setTransferEmail(@RequestParam("email") String email, Authentication auth) {
		
		if (auth.isAuthenticated()) {
			Account account = accountRepo.findByEmail(auth.getName());
			if (account.getAccountType().equals("S")) {
				Student student = studentRepo.findByAccount(account);
				student.setTransferEmail(email);
				studentRepo.save(student);
			}
		}
		
		return "redirect:/student/profile";
	}
	
	/**
	 * Load the parent's page
	 * @param auth
	 * @param model
	 * @return
	 */
	
	@GetMapping("/parent")
	public String loadParent(Authentication auth, Model model) {
		
		model.addAttribute("name", getName(auth));
		
		return "parent.html";
	}
	
	/**
	 * Load the admin's page
	 * @param auth
	 * @param model
	 * @return
	 */
	@GetMapping("/admin")
	public String loadAdmin(Authentication auth, Model model) {
		
		model.addAttribute("name", getName(auth));
		
		return "admin.html";
	}
}
