package in.sp.main.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {
@GetMapping("/get")
public String Display() {
	return "welcome in spring boot";
}
@GetMapping("/cont")
public String printCont(){
	return "78546921";
	
}
}
