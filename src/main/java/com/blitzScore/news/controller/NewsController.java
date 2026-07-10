package com.blitzScore.news.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blitzScore.news.controllerService.NewsApiControllerService;
import com.blitzScore.news.dto.ArticleNewsApi;
import com.blitzScore.news.service.NewsApiRepoService;

@RestController
@RequestMapping("/newsApi")
public class NewsController {
	@Autowired
	private NewsApiControllerService service;
	@Autowired
	private NewsApiRepoService reposervice;
	
	@GetMapping("/lastHour")
	public List<ArticleNewsApi> getFootballNewsApi(){
		
		return service.getFootballNewsLastHour();
	}
	
	
}
