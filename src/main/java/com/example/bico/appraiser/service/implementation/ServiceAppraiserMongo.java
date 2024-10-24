package com.example.bico.appraiser.service.implementation;

import com.example.bico.appraiser.model.Appraiser;
import com.example.bico.appraiser.model.mongo.AppraiserDocument;
import com.example.bico.appraiser.repository.AppraiserRepositoryMongo;
import com.example.bico.appraiser.service.ServiceAppraiser;
import com.example.bico.appraiser.util.BicoUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.Getter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceAppraiserMongo implements ServiceAppraiser {
    @Autowired
    private AppraiserRepositoryMongo appraiserRepository;

    @Override
    public Appraiser getAppraiser(String requestedId) {
        Optional<AppraiserDocument> optionalAppraiser = appraiserRepository.findById(requestedId);
        if (optionalAppraiser.isPresent()) {
            final AppraiserDocument appraiserDocument = optionalAppraiser.get();
            final Appraiser appraiser = new Appraiser(appraiserDocument.getId(),
                    appraiserDocument.getFirstName(),
                    appraiserDocument.getLastName(),
                    appraiserDocument.getCellphone(),
                    appraiserDocument.getProId());
            return appraiser;
        } else { // user is not found
            throw new RuntimeException("User not found!");
        }

    }

    @Override
    public String addAppraiser(Appraiser appraiser) {
        final AppraiserDocument appraiserDocument = new AppraiserDocument();
        appraiserDocument.setId(BicoUtilities.idGenerator(appraiser.firstName(), appraiser.lastName()));
        appraiserDocument.setFirstName(appraiser.firstName());
        appraiserDocument.setLastName(appraiser.lastName());
        appraiserDocument.setCellphone(appraiser.cellphone());
        appraiserDocument.setProId(appraiser.proId());

        final AppraiserDocument appraiserDocument1 = appraiserRepository.save(appraiserDocument);
        return "/appraisers/".concat(appraiserDocument1.getId());
    }

    @Override
    public List<Appraiser> getAll() {
//        return appraiserRepository.findAllNotDeleted(); // Fetch only active (non deleted) records
        List<AppraiserDocument> listAppraisersDocument = appraiserRepository.findByDeleted(false);
        List<Appraiser> listAppraisers = new ArrayList<>();
        for ( AppraiserDocument ad: listAppraisersDocument){
            Appraiser appraiser = new Appraiser(ad.getId(),ad.getFirstName(),ad.getLastName(),ad.getCellphone(),ad.getProId());
            listAppraisers.add(appraiser);
        }
        return listAppraisers;
    }




    @Override
    public void deleteAppraiserSoft(String deleteId) {
        Optional<AppraiserDocument> optionalAppraiserDocument = appraiserRepository.findById(deleteId);
        if(optionalAppraiserDocument.isPresent()){
            final AppraiserDocument appraiserDocument = optionalAppraiserDocument.get();
            appraiserDocument.setDeleted(true);
            appraiserRepository.save(appraiserDocument);
        }
        else{
            throw new RuntimeException("User not found!");
        }

    }

    @Override
    public void deleteAppraiserHard(String deleteId) {
        Optional<AppraiserDocument> optionalAppraiserDocument = appraiserRepository.findById(deleteId);
        if(optionalAppraiserDocument.isPresent()){
            appraiserRepository.deleteById(deleteId);

        }
        else{
            throw new RuntimeException("User not found!");
        }

    }

    @Override
    public Boolean isExpert(String searchId){
       Optional<AppraiserDocument> optionalAppraiserDocument = appraiserRepository.findById(searchId);
        if( optionalAppraiserDocument.isPresent()){
            final AppraiserDocument appraiserDocument = optionalAppraiserDocument.get();
            if(  appraiserDocument.getProId() == null || appraiserDocument.getProId().isEmpty() ){
                return false;
            }
            else {
                return true;
            }
        }
        else {
            return null;
        }



    }
}
