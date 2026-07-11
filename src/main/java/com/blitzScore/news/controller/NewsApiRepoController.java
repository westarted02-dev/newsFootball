package com.blitzScore.news.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.blitzScore.news.entity.Article;
import com.blitzScore.news.service.NewsApiRepoService;

@RestController
@RequestMapping("/news/repo")
public class NewsApiRepoController {

	@Autowired
	private NewsApiRepoService repo;

	@GetMapping("/all")
	public List<Article> getAllNews() {
		return repo.getAllNews();
	}
	
	@RequestMapping(value = "/dashbord", method = { RequestMethod.POST, RequestMethod.HEAD })
	public void dashbord() {
		System.out.print("live dashbord chaqirdi");
	}
}
