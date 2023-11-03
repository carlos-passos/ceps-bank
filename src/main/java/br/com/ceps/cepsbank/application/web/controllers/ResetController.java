package br.com.ceps.cepsbank.application.web.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RequestMapping("reset")
@RestController
public class ResetController {

    @PostMapping
    public ResponseEntity<Void> postReset() {
       return ResponseEntity.ok().build();
    }
}
