package jblog.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jblog.repository.CategoryRepository;
import jblog.repository.PostRepository;
import jblog.vo.CategoryVo;

@Service
public class CategoryService {
	private final CategoryRepository categoryRepository;
	private final PostRepository postRepository;
	
	public CategoryService(CategoryRepository categoryRepository, PostRepository postRepository) {
		this.categoryRepository = categoryRepository;
		this.postRepository = postRepository;
	}
	
	public List<CategoryVo> getCategoryList(String blogId) {
		return categoryRepository.findAll(blogId);
	}

	public void addCategory(CategoryVo categoryVo) {
		categoryRepository.insert(categoryVo);
	}

	@Transactional
	public void deleteCategory(Long categoryId) {
		postRepository.deleteByCategoryId(categoryId);
		categoryRepository.deleteById(categoryId);
	}

	public Long getDefaultCategoryId(String blogId) {
		return categoryRepository.findDefaultCategoryId(blogId);
	}

}
