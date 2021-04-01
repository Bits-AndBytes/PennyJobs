package ca.sheridancollege.pennyjobs.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ca.sheridancollege.pennyjobs.beans.Account;
import ca.sheridancollege.pennyjobs.beans.Role;
import ca.sheridancollege.pennyjobs.repositories.AccountRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	@Lazy
	private AccountRepository accountRepo;
	
	@Override
	public UserDetails loadUserByUsername(String firstName) throws UsernameNotFoundException {
		
		//Find a user based on the accountType
		ca.sheridancollege.pennyjobs.beans.Account account = accountRepo.findByFirstName(firstName);
		
		//If the user cannot be found
		if (account == null) {
			System.out.println("User not found:"+firstName);
			throw new UsernameNotFoundException("User " + firstName + "was not found in the database");
		}
		
		//Change the list of the user's roles into a list of GrantedAuthority
		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
		for(Role role : account.getRoles()) {
			grantList.add(new SimpleGrantedAuthority(role.getRolename()));
		}
		
		//Create a Spring User based on the information read
		UserDetails userDetails = new User(account.getPassword(), account.getEmail(), grantList);
		
		/*
		 * UserDetails userDetails = new User(account.getAccountType(),
		 * account.getEmail(), account.getEncryptedpassword(), account.getAddress(),
		 * account.getBirthdate(), account.getFirstname(), account.getLastname(),
		 * grantList);
		 */
	
		
		return userDetails;
	}

}
