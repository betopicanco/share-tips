package br.com.sharetips.repositories;

import br.com.sharetips.entities.Subject;
import br.com.sharetips.entities.Tip;
import br.com.sharetips.entities.User;
import br.com.sharetips.entities.dto.subject.SubjectDTO;
import br.com.sharetips.entities.dto.tip.TipFeedDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TipRepository extends JpaRepository<Tip, Long> {
    List<Tip> findByAuthor(User author);

    List<TipFeedDTO> findByTitle(String title);

    List<Tip> findByContentLike(String content);

    List<Tip> findByTitleLike(String title);

    List<Tip> findByAuthorIn(List<User> authors);

    List<Tip> findBySubjectsIn(List<Subject> subjects);

    List<Tip> findBySubjects(SubjectDTO subjectDTO);

    @Query("select t from Tip t inner join t.subjects subjects where upper(subjects.name) like upper(?1)")
    List<Tip> findBySubjectNameLike(String name);
}
