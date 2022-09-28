package br.com.sharetips.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sharetips.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	List<User> findAll();
	Optional<User> findByEmail(String email);
	Optional<User> findByEmailAndPassword(String email, String password);
}
