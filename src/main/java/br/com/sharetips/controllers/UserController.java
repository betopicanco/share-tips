package br.com.sharetips.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.sharetips.entities.User;
import br.com.sharetips.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService service;
	
//	@GetMapping("/")
//	public String helloWorld() {
//		return "Hello world";
//	}
	
	@GetMapping("/")
	public ResponseEntity<List<User>> findAll() {
		List<User> list = service.findAll();
		
		return ResponseEntity.ok().body(list);
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id) {
		User obj = service.findById(id);

		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping("/")
	public ResponseEntity<Void> insert(@RequestBody User obj) {
		service.save(obj);
		
		return ResponseEntity.ok().body(null);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.deleteById(id);

		return ResponseEntity.ok().body(null);
	}
}
