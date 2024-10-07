package com.example.bico.appraiser;

import com.example.bico.appraiser.model.Appraiser;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BicoAppraiserApplicationTests {
	@Autowired
	TestRestTemplate restTemplate;

	@Test
	void shouldReturnAnAppraiserWhenDataIsSend() {
		ResponseEntity<String>  response = restTemplate.getForEntity("/appraisers/80", String.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

		DocumentContext documentContext = JsonPath.parse(response.getBody());
		Number id =  documentContext.read("$.id");
		String f_name = documentContext.read("$.firstName");
		String l_name = documentContext.read("$.lastName");
		String cel =  documentContext.read("$.cellphone");
		String proId = documentContext.read("$.proId");
		assertThat(id).isEqualTo(80);
		assertThat(f_name).isEqualTo("Ignacio");
		assertThat(l_name).isEqualTo("Lopez");
		assertThat(cel).isEqualTo("6241666156");
		assertThat(proId).isEqualTo("1234AD");
	}

	@Test
	void shouldCreateANewAppraiser() {
		Appraiser newAppraiser =  new Appraiser(null,"Miguel", "Lopez","55123456","789AD");
		ResponseEntity<Void> createResponse = restTemplate.postForEntity("/appraisers", newAppraiser, Void.class);
		assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);

		URI locationOfNewAppraiser = createResponse.getHeaders().getLocation();
		ResponseEntity<String> getResponse = restTemplate.getForEntity(locationOfNewAppraiser,String.class);
		assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
}
