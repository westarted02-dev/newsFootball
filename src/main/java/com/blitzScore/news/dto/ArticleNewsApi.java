package com.blitzScore.news.dto;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;


@Component
public class ArticleNewsApi {

	private SourceArticle source;

	private String author;

	private String title;

	private String description;

	private String url;

	private String urlToImage;

	private LocalDateTime publishedAt;

	private String content;

	public SourceArticle getSource() {
		return source;
	}

	public void setSource(SourceArticle source) {
		this.source = source;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrlToImage() {
		return urlToImage;
	}

	public void setUrlToImage(String urlToImage) {
		this.urlToImage = urlToImage;
	}

	public LocalDateTime getPublishedAt() {
		return publishedAt;
	}

	public void setPublishedAt(LocalDateTime publishedAt) {
		this.publishedAt = publishedAt;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
}
