package br.com.sharetips.controllers;

import java.util.List;

import br.com.sharetips.entities.dto.subject.SubjectDTO;
import br.com.sharetips.entities.dto.user.UserLoggedDTO;
import br.com.sharetips.entities.dto.user.UserLoginRequestDTO;
import br.com.sharetips.entities.dto.user.UserRegisterRequestDTO;
import br.com.sharetips.entities.dto.user.UserUpdateRequestDTO;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.sharetips.entities.User;
import br.com.sharetips.services.UserService;

import javax.validation.Valid;

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
	public ResponseEntity<UserLoggedDTO> login(@RequestParam String email, @RequestParam String password) {
		UserLoggedDTO obj = service.login(email, password);
		return ResponseEntity.ok().body(obj);
	}
	@PostMapping("/")
	public ResponseEntity<User> register(@RequestBody @Valid UserRegisterRequestDTO dto) {
		User user = service.register(dto);
		
		return ResponseEntity.ok().body(user);
	}
	@PutMapping("/{id}")
	public ResponseEntity<User> update(@PathVariable Long id, @RequestBody UserUpdateRequestDTO dto) {
		User user = service.update(id, dto);

		return ResponseEntity.ok().body(user);
	}

	@PutMapping("/{id}/add-favorite-subjects")
	public ResponseEntity<User> addFavoriteSubjects(
			@PathVariable Long id,
			@RequestBody @Valid List<SubjectDTO> subjectDTO) {
		User user = service.addFavoriteSubject(id,subjectDTO);

		return ResponseEntity.ok().body(user);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.deleteById(id);

		return ResponseEntity.ok().body(null);
	}
}
