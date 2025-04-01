package in.sp.main.Service;


import java.util.Collections;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

import in.sp.main.Entity.Customer;
import in.sp.main.Repository.ServiceRepository;

@Service
public class CustomerService  implements UserDetailsService {
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private ServiceRepository repo;
	
	
	public boolean Register(Customer cus) {
	String encodeString=	passwordEncoder.encode(cus.getPassword());
	cus.setPassword(encodeString);
		Customer cusdata= repo.save(cus);
		
		return cusdata.getEmail()!=null;
		
	}


	@Override 
	//userdetails are used used to load the customer details from db and provide to the AuthenticationProvider

	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	    // Fetch customer from database
	    Customer c1 = repo.findByEmail(email);
	   
	    return new User(c1.getEmail(), c1.getPassword(), Collections.emptyList());
	}

}
