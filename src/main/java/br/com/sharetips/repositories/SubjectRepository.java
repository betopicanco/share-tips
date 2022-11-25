package br.com.sharetips.repositories;

import br.com.sharetips.entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    public Optional<Subject> findByName(String name);

    public List<Subject> findByNameLike(String name);

    @Query(value = "SELECT * FROM `subjects` s inner join tb_tip_subject tts on s.id = tts.subject_id GROUP by s.name ORDER BY COUNT(*) DESC;", nativeQuery = true)
    public List<Subject> findMostPopular();
}
