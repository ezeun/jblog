package jblog.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jblog.service.BlogService;
import jblog.service.CategoryService;
import jblog.service.FileUploadService;
import jblog.service.PostService;
import jblog.vo.BlogVo;
import jblog.vo.CategoryVo;
import jblog.vo.PostVo;

@Controller
@RequestMapping("/{id:(?!assets).*}") //사용자ID (/jblog/assets는 제외)
public class BlogController {
	private final BlogService blogService;
	private final CategoryService categoryService;
	private final PostService postService;
	private final FileUploadService fileUploadService;
	
	public BlogController(BlogService blogService,
						  CategoryService categoryService,
						  PostService postService,
						  FileUploadService fileUploadService) {
		this.blogService = blogService;
		this.categoryService = categoryService;
		this.postService = postService;
		this.fileUploadService = fileUploadService;
	}
	
	/* 블로그 메인페이지 */
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
		if(categoryId == 0L) { //default categoryId를 세팅
			categoryId = categoryService.getDefaultCategoryId(id);
		}
		if(postId == 0L) { //default postId를 세팅
			postId = postService.getDefaultPostId(categoryId);
		}
		System.out.println("\n\nBlogController.main(" + id + ", " + categoryId + ", " + postId + ")\n\n");
		
		model.addAttribute("blogVo", blogService.getBlog(id));
		model.addAttribute("categoryList", categoryService.getCategoryList(id));
		model.addAttribute("postList", postService.getPostList(categoryId));
		model.addAttribute("postVo", postService.getPost(postId));
		
		return "blog/main";
	}
	
	/* 블로그 관리 기본설정 페이지 */
	// @Auth 추천
	@GetMapping("/admin")
	public String adminDefault(
			@PathVariable("id") String id,
			Model model) {
		model.addAttribute("blogVo", blogService.getBlog(id));
		return "blog/admin-default";
	}
	@PostMapping("/admin/update")
	public String update(
						@PathVariable("id") String id,
						BlogVo blogVo,
						@RequestParam("file") MultipartFile file) {
		String profile = fileUploadService.restore(file);
		if(profile != null) {
			blogVo.setProfile(profile);
		}
		blogService.updateBlog(blogVo);
		
		return "redirect:/" + id; 
	}
	
	/* 블로그 관리 카테고리 페이지 */
	@GetMapping("/admin/category")
	public String adminCategory(
			@PathVariable("id") String id,
			Model model) {
		model.addAttribute("blogVo", blogService.getBlog(id));
		model.addAttribute("list", categoryService.getCategoryList(id));
		return "blog/admin-category";
	}
	@PostMapping("/admin/category/add")
	public String addCategory(
			@PathVariable("id") String id,
			CategoryVo categoryVo) {
		categoryService.addCategory(categoryVo);
		return "redirect:/" + id + "/admin/category"; 
	}
	@GetMapping("/admin/category/delete/{categoryId}")
	public String deleteCategory(
			@PathVariable("id") String id,
			@PathVariable("categoryId") Long categoryId) {
		categoryService.deleteCategory(categoryId);
		return "redirect:/" + id + "/admin/category"; 
	}
	
	/* 블로그 관리 글작성 페이지 */
	@GetMapping("/admin/write")
	public String adminWrite(
			@PathVariable("id") String id,
			Model model) {
		model.addAttribute("blogVo", blogService.getBlog(id));
		model.addAttribute("list", categoryService.getCategoryList(id));
		return "blog/admin-write";
	}
	@PostMapping("/admin/write")
	public String adminWritePost(
			@PathVariable("id") String id,
			PostVo postVo) {
		postService.addPost(postVo);
		return "redirect:/" + id;
	}
}
