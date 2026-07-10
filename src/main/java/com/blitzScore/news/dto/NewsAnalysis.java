package com.blitzScore.news.dto;

import java.util.List;



public record NewsAnalysis(
		NewsCategory category,        // örn: "Spor", "Ekonomi", "Politika"
        List<String> keywords,  // örn: ["John", "Barcelona", "transfer"]
        List<String> entities   // isim/kurum varlıkları ayrı istersen
) {}

enum NewsCategory {
    TRANSFER("Transfer"),
    SUPER_LIG("Super Lig"),
    CHAMPIONS_LEAGUE("Champions League"),
    PREMIER_LEAGUE("Premier League"),
    LA_LIGA("La Liga"),
    SERIE_A("Serie A");

    private final String label;

    NewsCategory(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}