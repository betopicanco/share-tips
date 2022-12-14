package br.com.sharetips.repositories;

import br.com.sharetips.entities.Tip;
import br.com.sharetips.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipRepository extends JpaRepository<Tip, Long> {
    List<Tip> findByAuthor(User author);
}
