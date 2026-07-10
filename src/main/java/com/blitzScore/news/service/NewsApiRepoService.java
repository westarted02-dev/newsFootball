package com.blitzScore.news.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blitzScore.news.entity.Article;
import com.blitzScore.news.repository.NewsApiRepository;

@Service
public class NewsApiRepoService {

	@Autowired
	private NewsApiRepository newsRepository;

	public List<Article> getAllNews() {
		return newsRepository.findAll();
	}
}
