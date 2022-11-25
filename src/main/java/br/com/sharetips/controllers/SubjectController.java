package br.com.sharetips.controllers;

import br.com.sharetips.entities.Subject;
import br.com.sharetips.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/subjects")
public class SubjectController {
    @Autowired
    private SubjectService subjectService;

    @GetMapping("/")
    public ResponseEntity<List<Subject>> findAll() {
        List<Subject> subjects = subjectService.findAll();

        return ResponseEntity.ok().body(subjects);
    }

    @GetMapping("/most-popular")
    public ResponseEntity<List<Subject>> findMostPopular() {
        List<Subject> subjects = subjectService.findMostPopular();

        return ResponseEntity.ok().body(subjects);
    }
}
