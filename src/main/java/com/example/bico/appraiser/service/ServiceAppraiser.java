package com.example.bico.appraiser.service;

import com.example.bico.appraiser.model.Appraiser;
import com.example.bico.appraiser.model.mongo.AppraiserDocument;

import java.util.List;

public interface ServiceAppraiser {

    AppraiserDocument appraiserIsThere(String findId);
    Appraiser getAppraiser(String requestedId);

    String addAppraiser(Appraiser newAppraiser);
    List<Appraiser> getAll();

    void deleteAppraiserSoft(String deleteId);

    void deleteAppraiserHard(String deleteId);

    Boolean isExpert(String searchId);

}
