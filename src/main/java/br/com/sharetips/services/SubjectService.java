package br.com.sharetips.services;

import br.com.sharetips.entities.Subject;
import br.com.sharetips.entities.dto.subject.SubjectDTO;
import br.com.sharetips.mappers.SubjectMapper;
import br.com.sharetips.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;


    public Subject saveToDTO(SubjectDTO dto) {
        Subject subject = SubjectMapper.INSTANCE.toSubject(dto);

        return subjectRepository.save(subject);
    }
    public List<Subject> findAll() {
        return subjectRepository.findAll();
    }

    public Optional<Subject> findByName(String name) {
        return subjectRepository.findByName(name);
    }
}
