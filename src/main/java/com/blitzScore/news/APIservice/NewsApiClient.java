package com.blitzScore.news.APIservice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import com.blitzScore.news.dto.ArticleNewsApi;
import com.blitzScore.news.dto.NewsResponseDto;

import reactor.core.publisher.Mono;

@Service
public class NewsApiClient {
	
	@Autowired
	private BaseApiService api;
	
	public Mono<List<ArticleNewsApi>> getFootballNewsLastHour(String firstTime , String atTheMoment) {
		
		ParameterizedTypeReference<NewsResponseDto<List<ArticleNewsApi>>> typeRef = new ParameterizedTypeReference<NewsResponseDto<List<ArticleNewsApi>>>() {
		};

		return api.get(
				uriBuilder -> uriBuilder.path("/everything").queryParam("q", "football").queryParam("from", firstTime)
						.queryParam("to", atTheMoment).queryParam("sortBy", "publishedAt").build(),
				typeRef).map(apiResponse -> {
					List<ArticleNewsApi> list = apiResponse.getArticles();
					if (list == null || list.isEmpty()) {
						throw new RuntimeException("No Article found");
					}
					return list;
				});
    }

}
