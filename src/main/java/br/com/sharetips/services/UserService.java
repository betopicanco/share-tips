package br.com.sharetips.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sharetips.entities.User;
import br.com.sharetips.repositories.UserRepository;
import br.com.sharetips.services.exceptions.ObjectNotFoundException;

@Service
public class UserService {
	@Autowired
	private UserRepository repository;
	
	public List<User> findAll() {
		return repository.findAll();
	}
	
	public User findById(Long id) {
		Optional<User> obj = repository.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("User not found!"));
	}
	
	public User save(User obj) {
		return repository.save(obj);
	}
}
