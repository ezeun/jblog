package jblog.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jblog.repository.BlogRepository;
import jblog.repository.CategoryRepository;
import jblog.repository.PostRepository;
import jblog.repository.UserRepository;
import jblog.vo.UserVo;

@Service
public class UserService {
	private UserRepository userRepository;
	private BlogRepository blogRepository;
	private CategoryRepository categoryRepository;
	private PostRepository postRepository;
	
	public UserService(UserRepository userRepository, 
					   BlogRepository blogRepository, 
					   CategoryRepository categoryRepository, 
					   PostRepository postRepository) {
		this.userRepository = userRepository;
		this.blogRepository = blogRepository;
		this.categoryRepository = categoryRepository;
		this.postRepository = postRepository;
	}

	@Transactional
	public void join(UserVo userVo) {
		userRepository.join(userVo);
		blogRepository.insert(userVo.getId());
		Long categoryId = categoryRepository.insertDefault(userVo.getId());
		postRepository.insertDefault(categoryId);
	}

	public UserVo getUser(String id, String password) {
		return userRepository.findByIdAndPassword(id, password);
	}

}
