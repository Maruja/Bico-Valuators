package com.example.bico.appraiser.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record Appraiser(
        String id,
        @NotBlank(message = "Appraiser's First Name cannot be blank.")
        @NotNull(message = "Appraiser's First Name cannot be null.")
        @Size(min = 3, max = 25, message = "Appraiser's First Name is Not between lengths 3 and 25.")
        String firstName,
        @NotBlank(message = "Appraiser's Last Name Cannot be blank.")
        @NotNull(message = "Appraiser's Last Name Cannot be null.")
        @Size(min = 3, max = 25, message = "Appraiser's Last Name is Not between lengths 3 and 25.")
        String lastName,
        String cellphone,
        String proId){

}