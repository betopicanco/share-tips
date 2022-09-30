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

    @PostMapping("/")
    private ResponseEntity<Tip> insert(@RequestBody @Valid TipCreateDTO dto) {
        Tip tip = service.save(dto);

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
    private ResponseEntity<Tip> addSubject(@PathVariable Long id, @RequestBody SubjectDTO subjectDTO) {
        Tip tip = service.addSubject(id, subjectDTO);

        return ResponseEntity.ok().body(tip);
    }
}
