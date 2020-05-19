package cb.lms.CB_Lms.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	
	@GetMapping("/hello")
	public String hello() {
		System.out.println("inside hello");
		
		return "Hello";
	}

}
