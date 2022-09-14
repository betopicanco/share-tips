package br.com.sharetips.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sharetips.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	public List<User> findAll();

	public User findByEmailAndPassword(String email, String password);
}
