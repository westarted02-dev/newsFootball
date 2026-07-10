package com.blitzScore.news.APIservice;

import java.net.URI;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;

import reactor.core.publisher.Mono;

@Service
public class BaseApiService {

	private final WebClient webClient;

    @Autowired
    public BaseApiService(@Qualifier("newsApiWebClient") WebClient webClient) {
        this.webClient = webClient;
    }
    
    /**
     * ✅ DÜZELTİLDİ - ParameterizedTypeReference desteği eklendi
     */
    public <T> Mono<T> get(String endpoint, ParameterizedTypeReference<T> responseType) {
        return webClient.get()
                .uri(endpoint)
                .retrieve()
                .bodyToMono(responseType)
                .doOnSuccess(response -> 
                    System.out.println("✅ API Call Successful: " + endpoint))
                .doOnError(error -> 
                    System.err.println("❌ API Error: " + endpoint + " - " + error.getMessage()));
    }

    /**
     * GET with query parameters - ParameterizedTypeReference version
     */
    public <T> Mono<T> get(String endpoint, Map<String, Object> queryParams, ParameterizedTypeReference<T> responseType) {
        return webClient.get()
                .uri(uriBuilder -> {
                    UriBuilder builder = uriBuilder.path(endpoint);
                    queryParams.forEach((key, value) -> builder.queryParam(key, value));
                    return builder.build();
                })
                .retrieve()
                .bodyToMono(responseType);
    }
    
    /**
     * GET with custom URI builder - ParameterizedTypeReference version  
     */
    public <T> Mono<T> get(Function<UriBuilder, URI> uriFunction, ParameterizedTypeReference<T> responseType) {
        return webClient.get()
                .uri(uriFunction)
                .retrieve()
                .bodyToMono(responseType);
    }

}
