package br.com.sharetips.services;

import br.com.sharetips.entities.Tip;
import br.com.sharetips.entities.User;
import br.com.sharetips.entities.dto.InsertTipDTO;
import br.com.sharetips.repositories.TipRepository;
import br.com.sharetips.repositories.UserRepository;
import br.com.sharetips.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TipService {
    @Autowired
    private TipRepository repository;

    @Autowired
    private UserRepository userRepository;

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

    public Tip insertFromDTO(InsertTipDTO obj) {
        User author = userRepository.findById(obj.getAuthorId()).get();
        Tip tip = obj.toTip(author);

        return repository.save(tip);
    }
}
