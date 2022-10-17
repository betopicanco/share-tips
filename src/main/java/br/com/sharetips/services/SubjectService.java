package br.com.sharetips.services;

import br.com.sharetips.entities.Subject;
import br.com.sharetips.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;

    public List<Subject> findAll() {
        return subjectRepository.findAll();
    }

    public Optional<Subject> findByName(String name) {
        return subjectRepository.findByName(name);
    }

    public Subject save(Subject subject) {
        return subjectRepository.save(subject);
    }

    public List<Subject> findByNameLike(String name) {
        return subjectRepository.findByNameLike(name);
    }
}
