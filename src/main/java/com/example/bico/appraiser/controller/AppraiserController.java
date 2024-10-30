package com.example.bico.appraiser.controller;

import com.example.bico.appraiser.model.Appraiser;
import com.example.bico.appraiser.service.ServiceAppraiser;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/appraisers")
class AppraiserController {
    private static final Logger logger = LoggerFactory.getLogger(AppraiserController.class);
    @Autowired
    private ServiceAppraiser serviceAppraiser;
    @GetMapping("/{requestedId}")
    private ResponseEntity<Appraiser> findById(@PathVariable String requestedId) {
        return ResponseEntity.ok(serviceAppraiser.getAppraiser(requestedId));

    }

    @PostMapping
    private ResponseEntity<Void> createAppraiser(@RequestBody @Valid Appraiser newAppraiser) {
        String uri = serviceAppraiser.addAppraiser(newAppraiser);
        return ResponseEntity.created(URI.create(uri)).build();
    }

    @GetMapping("")
    private ResponseEntity<List<Appraiser>> findAll() {
        return ResponseEntity.ok(serviceAppraiser.getAll());
    }

    @DeleteMapping("/{deleteId}/hard")
    private ResponseEntity<Void> deleteAppraiserHard(@PathVariable String deleteId){
        serviceAppraiser.deleteAppraiserHard(deleteId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{deleteId}/soft")
    private ResponseEntity<Void> deleteAppraiserSoft(@PathVariable String deleteId){
        serviceAppraiser.deleteAppraiserSoft(deleteId);
        return ResponseEntity.noContent().build();
    }

    //Make a endpoint for isExpert
    @GetMapping("/{findId}/isExpert")
    private ResponseEntity<Boolean> isExpert(@PathVariable String findId){
        return ResponseEntity.ok(serviceAppraiser.isExpert(findId));
    }
}
