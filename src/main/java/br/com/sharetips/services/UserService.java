package br.com.sharetips.services;

import java.util.List;
import java.util.Optional;

import br.com.sharetips.entities.Tip;
import br.com.sharetips.entities.dto.user.UserLoginRequestDTO;
import br.com.sharetips.entities.dto.user.UserRegisterRequestDTO;
import br.com.sharetips.exceptions.BadRequestException;
import br.com.sharetips.exceptions.ResourceNotFoundException;
import br.com.sharetips.mappers.UserMapper;
import br.com.sharetips.repositories.TipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sharetips.entities.User;
import br.com.sharetips.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository repository;

	@Autowired
	private TipRepository tipRepository;
	
	public List<User> findAll() {
		return repository.findAll();
	}
	
	public User findById(Long id) {
		Optional<User> obj = repository.findById(id);
		
		return obj.orElseThrow(() -> new ResourceNotFoundException("User not found"));
	}
	
	public User register(UserRegisterRequestDTO dto) {
		Optional<User> exists = repository.findByEmail(dto.getEmail());

		if(exists.isPresent()) {
			throw new BadRequestException("Email already exists");
		} else {
			User user = UserMapper.INSTANCE.toUser(dto);

			return repository.save(user);
		}
	}

	public User login(UserLoginRequestDTO dto) {
		Optional<User> user = repository.findByEmailAndPassword(dto.getEmail(), dto.getPassword());

		return user.orElseThrow(() -> new BadRequestException("Email ou senha incorretos"));
	}

	public void deleteById(Long id) {
		User user = findById(id);
		List<Tip> tips = tipRepository.findByAuthor(user);

		if(tips.isEmpty()) {
			repository.deleteById(id);
		}
	}

	public User update(Long id, User obj) {
		User entity = repository.getById(id);

		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPassword(obj.getPassword());

		return repository.save(entity);
	}
}
