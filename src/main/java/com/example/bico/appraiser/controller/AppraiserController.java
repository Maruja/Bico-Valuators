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
    private Appraiser findById(@PathVariable String requestedId) {
        return serviceAppraiser.getAppraiser(requestedId);

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

    @DeleteMapping("/{deleteId}")
    private ResponseEntity<Void> deleteAppraiser(@PathVariable String deleteId){
        serviceAppraiser.deleteAppraiser(deleteId);
        return ResponseEntity.noContent().build();
    }
}
