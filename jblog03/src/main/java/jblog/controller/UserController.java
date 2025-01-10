package jblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

	@GetMapping("/join")
	public String join() {
		return "user/join";
	}
	
	@PostMapping("/join")
	public String join(Model model) {
		//회원가입
		return "redirect:/user/joinsuccess";
	}
	
	@GetMapping("/joinsuccess")
	public String joinsuccess() {
		return "user/joinsuccess";
	}
	
	
	@GetMapping("/login")
	public String login() {
		return "user/login";
	}
	

}
