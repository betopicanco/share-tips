package br.com.sharetips.controllers;

import java.util.List;

import br.com.sharetips.entities.dto.user.UserLoginRequestDTO;
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
	@GetMapping("/login")
	public ResponseEntity<User> login(@RequestBody UserLoginRequestDTO dto) {
		User obj = service.login(dto);

		return ResponseEntity.ok().body(obj);
	}
	@PostMapping("/")
	public ResponseEntity<User> insert(@RequestBody User obj) {
		User user = service.save(obj);
		
		return ResponseEntity.ok().body(user);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.deleteById(id);

		return ResponseEntity.ok().body(null);
	}

	@PutMapping("/{id}")
	public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User obj) {
		obj = service.update(id, obj);

		return ResponseEntity.ok().body(obj);
	}
}
