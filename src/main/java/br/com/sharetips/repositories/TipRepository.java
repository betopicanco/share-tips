package br.com.sharetips.repositories;

import br.com.sharetips.entities.Subject;
import br.com.sharetips.entities.Tip;
import br.com.sharetips.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipRepository extends JpaRepository<Tip, Long> {
    List<Tip> findByAuthor(User author);

    @Query("select t from Tip t inner join t.subjects subjects where subjects.id = ?1")
    List<Tip> findBySubjectId(Long id);

    List<Tip> findByContentLike(String content);

    List<Tip> findByTitleLike(String title);

    List<Tip> findByAuthorIn(List<User> authors);

    List<Tip> findBySubjectsIn(List<Subject> subjects);

    @Query("select t from Tip t inner join t.subjects subjects where upper(subjects.name) like upper(?1)")
    List<Tip> findBySubjectNameLike(String name);

    @Query("select t from Tip t where t.author.id = ?1")
    List<Tip> findByAuthorId(Long id);

    List<Tip> findByAuthorNameLike(String name);
}
