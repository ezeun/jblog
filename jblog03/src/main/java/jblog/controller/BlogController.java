package jblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/blog") //사용자ID
public class BlogController {
	
	@GetMapping("")
	public String main() {
		return "blog/main";
	}
	
	@GetMapping("/admin")
	public String adminDefault() {
		return "blog/admin-default";
	}
	
	@GetMapping("/admin/category")
	public String adminCategory() {
		return "blog/admin-category";
	}
	
	@GetMapping("/admin/write")
	public String adminWrite() {
		return "blog/admin-write";
	}
}
