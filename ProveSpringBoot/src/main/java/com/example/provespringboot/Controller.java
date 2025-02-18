package com.example.provespringboot;

import com.example.provespringboot.model.Studente;
import com.example.provespringboot.repository.StudenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/studenti")
public class Controller {

    @Autowired
    private StudenteRepository studenteRepository;

    // Mostra tutti gli studenti
    @GetMapping
    public List<Studente> getAllStudenti() {
        return studenteRepository.findAll();
    }

    // Aggiungi un nuovo studente
    @PostMapping
    public Studente addStudente(@RequestBody Studente studente) {
        return studenteRepository.save(studente);
    }

    // Trova uno studente per email
    @GetMapping("/{email}")
    public Studente getStudenteByEmail(@PathVariable String email) {
        return studenteRepository.findByEmail(email);
    }
}
