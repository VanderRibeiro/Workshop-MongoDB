package com.simpli.workshopmongo.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simpli.workshopmongo.domain.Post;
import com.simpli.workshopmongo.services.PostService;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

	@Autowired
	private PostService service;

	// 1. MÉTODO PARA LISTAR TODOS (GET /posts)
	@GetMapping
	public ResponseEntity<List<Post>> findAll() {
		List<Post> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	// 2. MÉTODO PARA BUSCAR POR ID (GET /posts/{id})
	@GetMapping(value = "/{id}")
	public ResponseEntity<Post> findById(@PathVariable String id) {
		Post obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
}
