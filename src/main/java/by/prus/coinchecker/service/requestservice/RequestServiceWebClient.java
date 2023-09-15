package by.prus.coinchecker.service.requestservice;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

public class RequestServiceWebClient<T> {
    private final WebClient webClient;

    //does not work for OKX
    public RequestServiceWebClient() {
        this.webClient = WebClient.builder()
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public T doRequest(String url, Class<T> tClass) {
        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(tClass)
                .block();
    }

    public String doJsonRequest(String url) {
        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
