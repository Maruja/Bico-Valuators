package com.example.bico.appraiser.controller;

import com.example.bico.appraiser.model.IsExpertResponse;
import com.example.bico.appraiser.service.ServiceAppraiser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class AppraiserOpsController {

    @Autowired
    private ServiceAppraiser serviceAppraiser;


    @GetMapping("isExpert/{requestedId}")
    private ResponseEntity<IsExpertResponse> isExpert(@PathVariable String requestedId) {
        IsExpertResponse isExpertResponse = new IsExpertResponse();

        var result = serviceAppraiser.isExpert(requestedId);
        if (result == null){
            return ResponseEntity.notFound().build();
        } else{
            isExpertResponse.setIsExpert(String.valueOf(result));
            return ResponseEntity.ok(isExpertResponse);
        }
    }

}
