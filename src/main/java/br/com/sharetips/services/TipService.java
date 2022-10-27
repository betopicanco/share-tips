package br.com.sharetips.services;

import br.com.sharetips.entities.Subject;
import br.com.sharetips.entities.Tip;
import br.com.sharetips.entities.User;
import br.com.sharetips.entities.dto.subject.SubjectDTO;
import br.com.sharetips.entities.dto.tip.TipCreateDTO;
import br.com.sharetips.exceptions.BadRequestException;
import br.com.sharetips.exceptions.ResourceNotFoundException;
import br.com.sharetips.mappers.SubjectMapper;
import br.com.sharetips.repositories.SubjectRepository;
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
    private UserRepository userRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    public List<Tip> findAll() {
        return repository.findAll();
    }

    public Tip findById(Long id) {
        Optional<Tip> obj = repository.findById(id);

        return obj.orElseThrow(() -> new ResourceNotFoundException("Tip not found"));
    }

    // Salva uma nova dica a partir de um DTO
    public Tip saveFromDTO(TipCreateDTO dto) {
        User author = userRepository.findById(dto.getAuthorId()).orElseThrow(
                () -> new ResourceNotFoundException("Author not found")
        );
        Tip tip = dto.toTip(author);

        return repository.save(tip);
    }

    public Tip fromDTO(TipCreateDTO dto) {
        User author = userRepository.findById(dto.getAuthorId()).orElseThrow(
                () -> new ResourceNotFoundException("Author not found")
        );

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

    // Adiciona uma lista de assuntos a uma dica
    public Tip addSubjects(Long id, List<SubjectDTO> subjects) {
        Tip tip = findById(id);

        subjects.forEach((sub) -> {
            Optional<Subject> optSub =  subjectRepository.findByName(sub.getName());

            if(optSub.isPresent()) {
                tip.addSubject(optSub.get());
            } else {
                Subject newSubject = SubjectMapper.INSTANCE.toSubject(sub);

                tip.addSubject(subjectRepository.save(newSubject));
            }
        });

        return repository.save(tip);
    }

    // Busca uma lista de dicas pelo id de um author
    public List<Tip> findByAuthorId(Long authorId) {
        return repository.findByAuthorId(authorId);
    }

    // Busca por parametro e termo
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
                throw new BadRequestException("Parâmetro inválido");
        }

        return list;
    }

    // Busca pelo nome do author semelhante
    private List<Tip> findByAuthorNameLike(String name) {
        return repository.findByAuthorNameLike(name);
    }

    // Busca por titulo semelhante
    public List<Tip> findByTitleLike(String title) {
        return repository.findByTitleLike(title);
    }

    // Busca por conteúdo semelhante
    public List<Tip> findByContentLike(String content) {
        return repository.findByContentLike(content);
    }

    // Busca por assunto semelhante
    public List<Tip> findBySubjectNameLike(String subjectName) {
        return repository.findBySubjectNameLike(subjectName);
    }

    // Busca por assunto
    public List<Tip> findBySubjects(Long subjectId) {
        return repository.findBySubjectId(subjectId);
    }
}
