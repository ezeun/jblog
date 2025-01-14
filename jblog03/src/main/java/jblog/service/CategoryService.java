package jblog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import jblog.repository.CategoryRepository;
import jblog.vo.CategoryVo;

@Service
public class CategoryService {
	private final CategoryRepository categoryRepository;
	
	public CategoryService(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}
	
	public List<CategoryVo> getCategoryList(String blogId) {
		return categoryRepository.findAll(blogId);
	}

}
