package com.blitzScore.news.service;

import com.blitzScore.news.entity.Article;
import com.blitzScore.news.repository.NewsApiRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Service
public class GuardianFetcherService {

    private static final Logger log = LoggerFactory.getLogger(GuardianFetcherService.class);
    private static final String SOURCE = "The Guardian";
    private static final DateTimeFormatter GUARDIAN_FMT = DateTimeFormatter.ISO_DATE_TIME;

    private final NewsApiRepository repository;
    private final WebClient webClient;

    @Value("${guardian.api.key}")
    private String apiKey;

    public GuardianFetcherService(NewsApiRepository repository,
                                  @Qualifier("guardianWebClient") WebClient webClient) {
        this.repository = repository;
        this.webClient = webClient;
    }

    public void fetchAndSave() {
        LocalDateTime latestSaved = repository.findLatestPublishedAtBySource(SOURCE)
                .orElse(LocalDateTime.now().minusDays(1));

        String fromDate = latestSaved.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'"));

        Map<?, ?> response = webClient.get()
                .uri(uri -> uri.path("/search")
                        .queryParam("section", "football")
                        .queryParam("show-fields", "thumbnail,bodyText,byline")
                        .queryParam("order-by", "newest")
                        .queryParam("from-date", fromDate)
                        .queryParam("page-size", "50")
                        .queryParam("api-key", apiKey)
                        .build())
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        if (response == null) return;

        Map<?, ?> results = (Map<?, ?>) response.get("response");
        List<?> items = (List<?>) results.get("results");
        if (items == null) return;

        int saved = 0;
        for (Object item : items) {
            Map<?, ?> entry = (Map<?, ?>) item;
            String webUrl = (String) entry.get("webUrl");
            if (webUrl == null || repository.existsBySourceUrl(webUrl)) continue;

            Map<?, ?> fields = (Map<?, ?>) entry.get("fields");

            String publishedStr = (String) entry.get("webPublicationDate");
            LocalDateTime publishedAt = publishedStr != null
                    ? LocalDateTime.parse(publishedStr, GUARDIAN_FMT)
                    : LocalDateTime.now();

            Article article = new Article();
            article.setTitle((String) entry.get("webTitle"));
            article.setBody(fields != null ? (String) fields.get("bodyText") : null);
            article.setSource(SOURCE);
            article.setSourceUrl(webUrl);
            article.setPublishedAt(publishedAt);
            article.setImageUrl(fields != null ? (String) fields.get("thumbnail") : null);
            article.setAuthor(fields != null ? (String) fields.get("byline") : null);
            article.setCategory("Football");

            repository.save(article);
            saved++;
        }

        log.info("Guardian sync tamamlandı — {} yeni haber kaydedildi", saved);
    }
}
