package com.simpli.workshopmongo.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simpli.workshopmongo.domain.User;
import com.simpli.workshopmongo.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	@Autowired
	private UserService service;
	
	@GetMapping//anotação que direciona a função abaixo para o mapping /users
	public ResponseEntity<List<User>> findAll(){//responseEntity encapsula toda estrutura para retornar resposta http (com possiveis erros e cabeçalhos)
		List<User> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
}
