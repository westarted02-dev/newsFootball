package com.blitzScore.news.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class NewsResponseDto<T> {
	@JsonProperty("status")
    private String status;
	@JsonProperty("totalResults")
    private int totalResults;
	@JsonProperty("articles")
	private List<ArticleNewsApi> articles; // NewsAPI'deki "articles" dizisiyle birebir eşleşir

    // Getter ve Setter'lar
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public int getTotalResults() { return totalResults; }
    public void setTotalResults(int totalResults) { this.totalResults = totalResults; }

    public List<ArticleNewsApi> getArticles() { return articles; }
    public void setArticles(List<ArticleNewsApi> articles) { this.articles = articles; }
}