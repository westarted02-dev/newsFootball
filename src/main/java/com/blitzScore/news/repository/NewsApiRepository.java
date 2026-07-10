package com.blitzScore.news.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.blitzScore.news.entity.Article;

@Repository
public interface NewsApiRepository extends JpaRepository<Article, String> {

	boolean existsBySourceUrl(String sourceUrl);

	@Query("SELECT MAX(a.publishedAt) FROM Article a WHERE a.source = :source")
	Optional<LocalDateTime> findLatestPublishedAtBySource(@Param("source") String source);
}
