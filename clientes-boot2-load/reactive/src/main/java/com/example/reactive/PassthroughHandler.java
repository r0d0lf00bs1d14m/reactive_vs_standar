package com.example.reactive;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Component
public class PassthroughHandler {

    private final WebClient webClient;

    private final String targetHost = "http://localhost:8080";

    public PassthroughHandler() {
        this.webClient = WebClient
                .builder()
                .baseUrl(targetHost)
                .defaultCookie("cookieKey", "cookieValue")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }


    public Mono<MessageAck> handlePassthrough(Message message) {
        return webClient.post()
                .uri("/messages")
                .body(Mono.just(message), Message.class)
                .retrieve()
                .bodyToMono(MessageAck.class);
    }
}
