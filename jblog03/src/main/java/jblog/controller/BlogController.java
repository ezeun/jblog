package jblog.controller;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.ServletContext;
import jblog.service.BlogService;
import jblog.service.FileUploadService;
import jblog.vo.BlogVo;

@Controller
@RequestMapping("/{id:(?!assets).*}") //사용자ID (/jblog/assets는 제외)
public class BlogController {
	private final BlogService blogService;
	private final FileUploadService fileUploadService;
	private final ServletContext servletContext;
	private final ApplicationContext applicationContext;
	
	public BlogController(BlogService blogService,
						  FileUploadService fileUploadService,
						  ServletContext servletContext,
						  ApplicationContext applicationContext) {
		this.blogService = blogService;
		this.fileUploadService = fileUploadService;
		this.servletContext = servletContext;
		this.applicationContext = applicationContext;
	}
	
	@GetMapping({"", "/{path1}", "/{path1}/{path2}"})
	public String main(
			@PathVariable("id") String id,
			@PathVariable("path1") Optional<Long> path1,
			@PathVariable("path2") Optional<Long> path2,
			Model model) {
		
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
		
		model.addAttribute("blogVo", blogService.getBlog(id));
		return "blog/main";
	}
	
	// @Auth 추천
	@GetMapping("/admin")
	public String adminDefault(
			@PathVariable("id") String id,
			Model model) {
		model.addAttribute("blogVo", blogService.getBlog(id));
		return "blog/admin-default";
	}
	
	@PostMapping("/admin/update")
	public String update(BlogVo blogVo,
						@RequestParam("file") MultipartFile file) {
		String profile = fileUploadService.restore(file);
		if(profile != null) {
			blogVo.setProfile(profile);
		}
		blogService.updateBlog(blogVo);
		
		// update servlet context bean
//		servletContext.setAttribute("blogVo", blogVo);
		
		// update appliction context bean
//		BlogVo blog = applicationContext.getBean(BlogVo.class);
//		BeanUtils.copyProperties(blogVo, blog);
		
		String blogId = blogVo.getBlogId();
		return "redirect:/" + blogId; //수정
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
