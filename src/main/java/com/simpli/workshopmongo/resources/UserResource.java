package com.simpli.workshopmongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.simpli.workshopmongo.domain.User;
import com.simpli.workshopmongo.dto.UserDTO;
import com.simpli.workshopmongo.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired
	private UserService service;

	@GetMapping // anotação que direciona a função abaixo para o mapping /users
	public ResponseEntity<List<UserDTO>> findAll() {// responseEntity encapsula toda estrutura para retornar resposta
													// http (com possiveis erros e cabeçalhos)
		List<User> list = service.findAll();
		List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}

	//Identificar
	@GetMapping(value = "/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable String id) {//pathvariable garante que o id do parametro seja o mesmo do map
		User obj = service.findById(id);
		return ResponseEntity.ok().body(new UserDTO(obj));
	}
	
	//Insert
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody UserDTO objDto) {//Pega o body da requisição HTTP e converte a obj java como parametro
		User obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();//cria um URL para o ID criado
		return ResponseEntity.created(uri).build();
	}
	
	//Update
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@PathVariable String id, @RequestBody UserDTO objDto) {//Pega o body da requisição HTTP e converte a obj java como parametro
		User obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	//Delete
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) {//pathvariable garante que o id do parametro seja o mesmo do map
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}