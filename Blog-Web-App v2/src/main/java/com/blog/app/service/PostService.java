package com.blog.app.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.app.models.Post;
import com.blog.app.repository.PostRepository;

@Service
public class PostService {
	
	@Autowired
	public PostRepository postRepository;
	
	
	
	public PostService() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public PostService(PostRepository postRepository) {
		super();
		this.postRepository = postRepository;
	}

	public Optional<Post> getById(Long id){
		return postRepository.findById(id);
	}
	
	public List<Post>getAll(){
		return postRepository.findAll();
	}
	
	public void delete(Post post) {
		postRepository.delete(post);
	}
	
	public Post save(Post post) {
		if(post.getId() == null) {
			post.setCreatedAt(LocalDateTime.now());
		}
		
		return postRepository.save(post);
	}
}
