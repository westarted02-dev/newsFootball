package com.blitzScore.news.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "articles")
public class Article {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;

	@Column(name = "title", columnDefinition = "NVARCHAR(MAX)")
	private String title;

	@Column(name = "body", columnDefinition = "NVARCHAR(MAX)")
	private String body;

	@Column(name = "summary", columnDefinition = "NVARCHAR(MAX)")
	private String summary;

	@Column(name = "category", length = 100)
	private String category;

	@Column(name = "source", length = 255)
	private String source;

	@Column(name = "source_url", length = 500, unique = true)
	private String sourceUrl;

	@Column(name = "author", length = 255)
	private String author;

	@Column(name = "published_at")
	private LocalDateTime publishedAt;

	@Column(name = "image_url", length = 500)
	private String imageUrl;

	@Column(name = "emoji", length = 10)
	private String emoji;

	@Column(name = "language", length = 5)
	private String language;

	public String getId() { return id; }
	public void setId(String id) { this.id = id; }

	public String getTitle() { return title; }
	public void setTitle(String title) { this.title = title; }

	public String getBody() { return body; }
	public void setBody(String body) { this.body = body; }

	public String getSummary() { return summary; }
	public void setSummary(String summary) { this.summary = summary; }

	public String getCategory() { return category; }
	public void setCategory(String category) { this.category = category; }

	public String getSource() { return source; }
	public void setSource(String source) { this.source = source; }

	public String getSourceUrl() { return sourceUrl; }
	public void setSourceUrl(String sourceUrl) { this.sourceUrl = sourceUrl; }

	public String getAuthor() { return author; }
	public void setAuthor(String author) { this.author = author; }

	public LocalDateTime getPublishedAt() { return publishedAt; }
	public void setPublishedAt(LocalDateTime publishedAt) { this.publishedAt = publishedAt; }

	public String getImageUrl() { return imageUrl; }
	public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

	public String getEmoji() { return emoji; }
	public void setEmoji(String emoji) { this.emoji = emoji; }

	public String getLanguage() { return language; }
	public void setLanguage(String language) { this.language = language; }
}
