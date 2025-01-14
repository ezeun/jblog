package jblog.service;

import org.springframework.stereotype.Service;

import jblog.repository.BlogRepository;
import jblog.vo.BlogVo;

@Service
public class BlogService {
	private BlogRepository blogRepository;
	
	public BlogService(BlogRepository blogRepository) {
		this.blogRepository = blogRepository;
	}
	
	
	public BlogVo getBlog(String id) {
		return blogRepository.findOne(id);
	}


	public void updateBlog(BlogVo blogVo) {
		blogRepository.update(blogVo);
	}
}
