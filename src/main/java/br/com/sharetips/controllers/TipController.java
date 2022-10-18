package br.com.sharetips.controllers;
import br.com.sharetips.entities.Tip;
import br.com.sharetips.entities.dto.subject.SubjectDTO;
import br.com.sharetips.entities.dto.tip.TipCreateDTO;
import br.com.sharetips.services.TipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/tips")
public class TipController {
    @Autowired
    private TipService service;

    @GetMapping("/")
    private ResponseEntity<List<Tip>> findAll() {
        List<Tip> list = service.findAll();

        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    private ResponseEntity<Tip> findById(@PathVariable Long id) {
        Tip obj = service.findById(id);

        return ResponseEntity.ok().body(obj);
    }

    // TODO check if term accept 2 or more words
    @GetMapping("/search")
    private ResponseEntity<List<Tip>> findByParamAndTerm(
            @RequestParam String param,
            @RequestParam String term) {
        List<Tip> list = service.findByParamAndTerm(param, term);

        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/find-by-author/{userId}")
    private ResponseEntity<List<Tip>> findByAuthor(@PathVariable Long userId) {
        List<Tip> list = service.findByAuthorId(userId);

        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/find-by-subject/{subjectId}")
    private ResponseEntity<List<Tip>> findBySubjects(@PathVariable Long subjectId) {
        List<Tip> list = service.findBySubjects(subjectId);

        return ResponseEntity.ok().body(list);
    }

    @PostMapping("/")
    private ResponseEntity<Tip> insert(@RequestBody @Valid TipCreateDTO dto) {
        Tip tip = service.saveFromDTO(dto);

        return ResponseEntity.ok().body(tip);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);

        return ResponseEntity.ok().body(null);
    }

    @PutMapping("/{id}")
    private ResponseEntity<Tip> update(@PathVariable Long id, @RequestBody TipCreateDTO dto) {
        Tip tip = service.update(id, dto);

        return ResponseEntity.ok().body(tip);
    }

    @PutMapping("/{id}/add-subject")
    private ResponseEntity<Tip> addSubject(@PathVariable Long id, @RequestBody @Valid SubjectDTO subjectDTO) {
        Tip tip = service.addSubject(id, subjectDTO);

        return ResponseEntity.ok().body(tip);
    }
}
