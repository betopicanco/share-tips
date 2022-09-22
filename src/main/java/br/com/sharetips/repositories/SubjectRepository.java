package br.com.sharetips.repositories;

import br.com.sharetips.entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    public Optional<Subject> findByName(String name);
}
