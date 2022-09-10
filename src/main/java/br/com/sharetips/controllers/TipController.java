package br.com.sharetips.controllers;

import br.com.sharetips.entities.Tip;
import br.com.sharetips.services.TipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    private ResponseEntity<Tip> insert(@RequestBody Tip obj) {
        Tip tip = service.insert(obj);

        return ResponseEntity.ok().body(tip);
    }
}
