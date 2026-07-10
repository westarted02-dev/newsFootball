package com.blitzScore.news.dto;

import org.springframework.stereotype.Component;

@Component
public class SourceArticle {

    private String id;  
    private String name;
	public String getSourceId() {
		return id;
	}
	public void setSourceId(String sourceId) {
		this.id = sourceId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}  


}
