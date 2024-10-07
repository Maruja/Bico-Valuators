package com.example.bico.appraiser.service.implementation;

import com.example.bico.appraiser.model.Appraiser;
import com.example.bico.appraiser.service.ServiceAppraiser;
import com.example.bico.appraiser.util.BicoUtilities;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class ServiceAppraiserMemory implements ServiceAppraiser {
    private final List<Appraiser> appraiserList;

    public ServiceAppraiserMemory() {
        appraiserList =  new ArrayList<>();
    }

    public Appraiser getAppraiser(String requestedId) {
        for(Appraiser appraiser : appraiserList){
            if (requestedId.equals(appraiser.id())){
                return appraiser;
            }
        }
        return null;
    }

    public String addAppraiser(Appraiser newAppraiser) {
        BicoUtilities utility = new BicoUtilities();
        String newId = utility.idGenerator(newAppraiser.firstName(),newAppraiser.lastName());
        Appraiser appraiserWithCustomId = new Appraiser(newId,newAppraiser.firstName(), newAppraiser.lastName(), newAppraiser.cellphone(), newAppraiser.proId());
        appraiserList.add(appraiserWithCustomId);
        return "/appraisers/".concat(newId);
    }

    public List<Appraiser> getAll() {
        return appraiserList;
    }
}
