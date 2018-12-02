package org.poc.imageservice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestTemplateTest {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${imgur.client.id}")
    private String clientID;

    @Value("${imgur.image.resource}")
    private String resource;

    @Test
    @Description("Using an existing image submitted to imgur service")
    public void setRestTemplateGetTest(){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", clientID);
        HttpEntity<Object> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response =
                restTemplate.exchange(resource + "/aBwxsDt", HttpMethod.GET, entity, String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }
}
