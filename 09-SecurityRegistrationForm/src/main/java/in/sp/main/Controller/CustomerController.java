package in.sp.main.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import in.sp.main.Entity.Customer;
import in.sp.main.Service.CustomerService;
import in.sp.main.Service.JwtUtil;

@RestController
public class CustomerController {
	
	@Autowired
	private JwtUtil jwt;
	@Autowired
	private CustomerService serv;
	
	@Autowired
	private AuthenticationManager AuthManager;
	
	@PostMapping("/register")
	public ResponseEntity<String>Customerreg(@RequestBody Customer c){
		boolean status=serv.Register(c);
		if(status) {
			return new ResponseEntity<>("success",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>("not saved faild",HttpStatus.OK);
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<String>Login(@RequestBody Customer c){
		UsernamePasswordAuthenticationToken token=new UsernamePasswordAuthenticationToken(c.getEmail(), c.getPassword());
		//varify login valid or not
		
		try {
		Authentication auth = AuthManager.authenticate(token);
//		boolean staus=auth.isAuthenticated();
//		if(staus) {
//			return new ResponseEntity<>("Welcome",HttpStatus.OK);
//		}
//		else {
//			return new ResponseEntity<>("failed",HttpStatus.OK);
//		}
		
		if(auth.isAuthenticated()) {
			String jwtToken= jwt.generateToken(c.getEmail());
			
			return new ResponseEntity<String>(jwtToken,HttpStatus.OK);
		}
		}
		catch(Exception e) {
			
		}
		return new ResponseEntity<String>("Invalid Credential",HttpStatus.BAD_REQUEST);

	}
	
	@GetMapping("/map")
	public String Dispaly() {
		return "welcom to Spring security";
	}
}
