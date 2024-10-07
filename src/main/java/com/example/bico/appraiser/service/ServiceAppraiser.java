package com.example.bico.appraiser.service;

import com.example.bico.appraiser.model.Appraiser;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ServiceAppraiser {
    Appraiser getAppraiser(String requestedId);
    String addAppraiser(Appraiser newAppraiser);
    List<Appraiser> getAll();

}
