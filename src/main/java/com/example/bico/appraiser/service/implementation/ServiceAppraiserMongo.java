package com.example.bico.appraiser.service.implementation;

import com.example.bico.appraiser.model.Appraiser;
import com.example.bico.appraiser.model.mongo.AppraiserDocument;
import com.example.bico.appraiser.repository.AppraiserRepositoryMongo;
import com.example.bico.appraiser.service.ServiceAppraiser;
import com.example.bico.appraiser.util.BicoUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceAppraiserMongo implements ServiceAppraiser {
    @Autowired
    private AppraiserRepositoryMongo appraiserRepository;
    @Override
    public Appraiser getAppraiser(String requestedId) {
        return null;
    }

    @Override
    public String addAppraiser(Appraiser appraiser) {
        final AppraiserDocument appraiserDocument = new AppraiserDocument();
        appraiserDocument.setId(BicoUtilities.idGenerator(appraiser.firstName(),appraiser.lastName()));
        appraiserDocument.setFirstName(appraiser.firstName());
        appraiserDocument.setLastName(appraiser.lastName());
        appraiserDocument.setCellphone(appraiser.cellphone());
        appraiserDocument.setProId(appraiser.proId());

        final AppraiserDocument appraiserDocument1 = appraiserRepository.save(appraiserDocument);

        return "/appraisers/".concat(appraiserDocument1.getId());
    }

    @Override
    public List<Appraiser> getAll() {
        return appraiserRepository
                .findAll()
                .stream()
                .map(appraiserDocument -> new Appraiser(appraiserDocument.getId(),appraiserDocument.getFirstName(),appraiserDocument.getLastName(),appraiserDocument.getCellphone(),appraiserDocument.getProId()))
                .toList();
    }
}
