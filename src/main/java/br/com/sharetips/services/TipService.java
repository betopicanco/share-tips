package br.com.sharetips.services;

import br.com.sharetips.entities.Tip;
import br.com.sharetips.repositories.TipRepository;
import br.com.sharetips.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipService {
    @Autowired
    private TipRepository repository;

    public List<Tip> findAll() {
        return repository.findAll();
    }

    public Tip findById(Long id) {
        Optional<Tip> obj = repository.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException("Tip not found"));
    }

    public Tip insert(Tip obj) {
        return repository.save(obj);
    }
}