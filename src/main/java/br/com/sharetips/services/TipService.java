package br.com.sharetips.services;

import br.com.sharetips.entities.Subject;
import br.com.sharetips.entities.Tip;
import br.com.sharetips.entities.User;
import br.com.sharetips.entities.dto.subject.SubjectDTO;
import br.com.sharetips.entities.dto.tip.TipCreateDTO;
import br.com.sharetips.entities.dto.tip.TipFeedDTO;
import br.com.sharetips.exceptions.ResourceNotFoundException;
import br.com.sharetips.mappers.SubjectMapper;
import br.com.sharetips.repositories.TipRepository;
import br.com.sharetips.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipService {
    @Autowired
    private TipRepository repository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SubjectService subjectService;

    public List<Tip> findAll() {
        return repository.findAll();
    }

    public Tip findById(Long id) {
        Optional<Tip> obj = repository.findById(id);

        return obj.orElseThrow(() -> new ResourceNotFoundException("Tip not found"));
    }

    public Tip save(TipCreateDTO dto) {
        return repository.save(fromDTO(dto));
    }

    public Tip fromDTO(TipCreateDTO dto) {
        User author = findAuthorById(dto.getAuthorId());

        return dto.toTip(author);
    }

    public void deleteById(Long id) {
        findById(id);
        repository.deleteById(id);
    }

    public Tip update(Long id, TipCreateDTO dto) {
        Tip tip = findById(id);
        Tip obj = fromDTO(dto);

        tip.setTitle(obj.getTitle());
        tip.setContent(obj.getContent());
        tip.setAuthor(obj.getAuthor());

        return repository.save(tip);
    }

    public Tip addSubject(Long id, SubjectDTO subjectDTO) {
        Tip tip = findById(id);
        Optional<Subject> subject = subjectService.findByName(subjectDTO.getName());

        if(subject.isPresent()) {
            tip.addSubject(subject.get());
        } else {
            Subject newSubject = subjectService.save(SubjectMapper.INSTANCE.toSubject(subjectDTO));
            tip.addSubject(newSubject);
        }

        return repository.save(tip);
    }

    public List<Tip> findByAuthor(Long authorId) {
        User author = findAuthorById(authorId);

        return repository.findByAuthor(author);
    }

    public List<TipFeedDTO> findFeedByUser() {
        return repository.findByTitle("titulo");
    }

    public List<Tip> findByParamAndTerm(String param, String term) {
        List<Tip> list;
        term = "%" + term + "%";

        switch (param) {
            case "author":
                list = findByAuthorNameLike(term);
                break;
            case "title":
                list = findByTitleLike(term);
                break;
            case "content":
                list = findByContentLike(term);
                break;
            case "subject":
                list = findBySubjectNameLike(term);
                break;
            default:
                throw new RuntimeException("Parâmetro inválido");
        }

        return list;
    }

    private List<Tip> findByAuthorNameLike(String name) {
        List<User> authors = userService.findByNameLike(name);

        return repository.findByAuthorIn(authors);
    }

    public List<Tip> findByTitleLike(String title) {
        return repository.findByTitleLike(title);
    }

    public List<Tip> findByContentLike(String content) {
        return repository.findByContentLike(content);
    }

    public List<Tip> findBySubjectNameLike(String subjectName) {
        List<Tip> tips = repository.findBySubjectNameLike(subjectName);

        return tips;
    }

    public List<Tip> findBySubjects(SubjectDTO subjectDTO) {
        List<Tip> tips = repository.findBySubjects(subjectDTO);

        return tips;
    }
}
