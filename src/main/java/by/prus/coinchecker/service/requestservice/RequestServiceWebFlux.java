package by.prus.coinchecker.service.requestservice;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class RequestServiceWebFlux<T> {
    private final WebClient webClient;

    public RequestServiceWebFlux() {
        this.webClient = WebClient.builder()
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public Mono<T> doRequest(String url, Class<T> tClass) {
        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(tClass);
    }

    public Mono<String> doJsonRequest(String url) {
        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class);
    }
}
