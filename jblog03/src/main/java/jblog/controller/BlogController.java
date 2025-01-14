package jblog.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/{id:(?!assets).*}") //사용자ID (/jblog/assets는 제외)
public class BlogController {
	
	@GetMapping({"", "/{path1}", "/{path1}/{path2}"})
	public String main(
			@PathVariable("id") String id,
			@PathVariable("path1") Optional<Long> path1,
			@PathVariable("path2") Optional<Long> path2) {
		
		Long categoryId = 0L;
		Long postId= 0L;
		
		if(path1.isPresent()) {
			categoryId = path1.get();
		}
		if(path2.isPresent()){
			postId = path2.get();
		}
		
		// 서비스에서~
		// categoryId == 0L 이면 default categoryId를 세팅
		// postId == 0L 이면 default postId를 세팅
		System.out.println("BlogController.main(" + id + ", " + categoryId + ", " + postId + ")");
		return "blog/main";
	}
	
	// @Auth 추천
	@GetMapping("/admin")
	public String adminDefault(
			@PathVariable("id") String id) {
		return "blog/admin-default";
	}
	
	@GetMapping("/admin/category")
	public String adminCategory(
			@PathVariable("id") String id) {
		return "blog/admin-category";
	}
	
	@GetMapping("/admin/write")
	public String adminWrite(
			@PathVariable("id") String id) {
		return "blog/admin-write";
	}
}
