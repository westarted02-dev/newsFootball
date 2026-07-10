package com.blitzScore.news.config;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import io.netty.channel.ChannelOption;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

@Service
public class APIConfig {

	@Value("${rapidapi.base-url}")
    private String baseUrl;

    @Value("${rapidapi.key}")
    private String apiKey;

    @Value("${app.api.timeout}")
    private int timeout;

	
	@Bean
	public WebClient guardianWebClient() {
		return WebClient.builder()
				.baseUrl("https://content.guardianapis.com")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.build();
	}

	@Bean
    public WebClient newsApiWebClient() {

        HttpClient httpClient = HttpClient.create()
                .responseTimeout(Duration.ofMillis(timeout))
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, timeout);

        return WebClient.builder()
                .baseUrl(baseUrl)
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader("X-Api-Key", apiKey)
                .defaultHeader(HttpHeaders.USER_AGENT, "LiveScoreApp/1.0")
                .exchangeStrategies(ExchangeStrategies.builder()
                        .codecs(config -> config.defaultCodecs()
                                .maxInMemorySize(16 * 1024 * 1024)) // ✅ EKLEDİK
                        .build())
                .filter(logRequest())
                .filter(logResponse())
                .build();
    }
	

    // Optional: Request logging
    private static ExchangeFilterFunction logRequest() {
        return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
            System.out.println("🚀 Request: " + clientRequest.method() + " " + clientRequest.url());
            
            return Mono.just(clientRequest);
        });
    }

    // Optional: Response logging
    private static ExchangeFilterFunction logResponse() {
        return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
            System.out.println("📥 Response: " + clientResponse.statusCode());
            return Mono.just(clientResponse);
        });
    }

	
}
