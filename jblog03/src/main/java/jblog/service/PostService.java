package jblog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import jblog.repository.PostRepository;
import jblog.vo.PostVo;

@Service
public class PostService {
	private PostRepository postRepository;
	
	public PostService(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	public void addPost(PostVo postVo) {
		postRepository.insert(postVo);
	}

	public Long getDefaultPostId(Long categoryId) {
		return postRepository.findDefaultPostId(categoryId);
	}

	public List<PostVo> getPostList(Long categoryId) {
		return postRepository.findAll(categoryId);
	}

	public PostVo getPost(Long postId) {
		return postRepository.findOne(postId);
	}
	
}
