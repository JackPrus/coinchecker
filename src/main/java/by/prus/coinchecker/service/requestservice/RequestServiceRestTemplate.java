package by.prus.coinchecker.service.requestservice;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class RequestServiceRestTemplate<T> {

    public T doRequest(String url, String requestBody, Class <T> tClass) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<T> response = restTemplate.exchange(url, HttpMethod.GET, entity, tClass);
        T responseBody = response.getBody();
        return responseBody;
    }

    ;

    public String doJsonRequest(String url, String requestBody) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        String responseBody = response.getBody();
        return responseBody;
    }
}
