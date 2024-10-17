package com.example.bico.appraiser.model.mongo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@Document(collection = "appraiser")
public class AppraiserDocument {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String cellphone;
    private String proId;
}
