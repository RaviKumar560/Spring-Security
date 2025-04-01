package in.sp.main.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.sp.main.Entity.User;
import in.sp.main.Repository.UserRepository;

@RestController
public class UserController {

	@Autowired
	private UserRepository userRepo;
	
	@PostMapping("/student")
	public User SaveData(@RequestBody User user) {
		System.out.println("inserting in db");
		User student=userRepo.save(user);
		return student;
	}
	
	@GetMapping("/teacher")
	public List<User> getAll() {
		return userRepo.findAll();
	}
}
