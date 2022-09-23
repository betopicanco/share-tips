package br.com.sharetips.services;

import br.com.sharetips.entities.Subject;
import br.com.sharetips.entities.Tip;
import br.com.sharetips.entities.User;
import br.com.sharetips.entities.dto.SubjectDTO;
import br.com.sharetips.entities.dto.TipDTO;
import br.com.sharetips.exceptions.ResourceNotFoundException;
import br.com.sharetips.repositories.SubjectRepository;
import br.com.sharetips.repositories.TipRepository;
import br.com.sharetips.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class TipService {
    @Autowired
    private TipRepository repository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Tip> findAll() {
        return repository.findAll();
    }

    public Tip findById(Long id) {
        Optional<Tip> obj = repository.findById(id);

        return obj.orElseThrow(() -> new ResourceNotFoundException("Tip not found"));
    }

    public Tip save(TipDTO dto) {
        return repository.save(fromDTO(dto));
    }

    public Tip fromDTO(TipDTO dto) {
        User author = userRepository.findById(dto.getAuthorId()).get();

        return dto.toTip(author);
    }

    public void deleteById(Long id) {
        findById(id);
        repository.deleteById(id);
    }

    public Tip update(Long id, TipDTO dto) {
        Tip tip = findById(id);
        Tip obj = fromDTO(dto);

        tip.setTitle(obj.getTitle());
        tip.setContent(obj.getContent());
        tip.setAuthor(obj.getAuthor());

        return repository.save(tip);
    }

    public Tip addSubject(Long id, SubjectDTO subjectDTO) {
        Tip tip = findById(id);
        Optional<Subject> subject = subjectRepository.findByName(subjectDTO.getName());

        if(subject.isPresent()) {
            tip.addSubject(subject.get());
        } else {
            Subject newSubject = subjectRepository.save(subjectDTO.toSubject());
            tip.addSubject(newSubject);
        }

        return repository.save(tip);
    }
}
