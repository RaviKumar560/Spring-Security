package in.sp.main.Controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class SecurityController {

	@GetMapping("/rest")
	public String display() {
		
		HttpHeaders headers=new HttpHeaders()	;
		headers.setBasicAuth("ravi","ravi@123");
		HttpEntity<String>reqEntity=new HttpEntity<>(headers);
		
		String uri ="http://localhost:9947/get";
		
		
		RestTemplate rtm =new RestTemplate();
		ResponseEntity<String>forEntity=rtm.exchange(uri,HttpMethod.GET,reqEntity,String.class);
		
		return forEntity.getBody();
	}
}
