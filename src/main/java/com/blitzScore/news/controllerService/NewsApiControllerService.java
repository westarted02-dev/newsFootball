package com.blitzScore.news.controllerService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blitzScore.news.APIservice.NewsApiClient;
import com.blitzScore.news.dto.ArticleNewsApi;

@Service
public class NewsApiControllerService {

	@Autowired
	private NewsApiClient service;

	public List<ArticleNewsApi> getFootballNewsLastHour() {
		LocalDateTime simdi = LocalDateTime.now(); 
		LocalDateTime last = LocalDateTime.now().minusHours(1); 

        // 2. Nanosaniyeyi SIFIRLA (Küsüratı tamamen uçurur)
        LocalDateTime temizZaman = simdi.withNano(0);
        LocalDateTime lastHour = last.withNano(0);
        
        // 3. String'e çevir
        String sonuc = temizZaman.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        String lastHourString = temizZaman.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        
        
        // Çıktı tam istediğin gibi olur: 2026-06-29T13:00:39
		
		return service.getFootballNewsLastHour("2026-06-28T01:05:31", sonuc).block();
		
	}

}
