package com.ghifar.api.api.core.recommendation.dto;

public class Recommendation {
    private final int productId;
    private final int recommendationId;
    private final int rate;
    private final String author;
    private final String content;
    private final String serviceAddress;

    public Recommendation() {
        productId=0;
        recommendationId=0;
        author=null;
        rate=0;
        content=null;
        serviceAddress=null;

    }

    public Recommendation(int productId, int recommendationId, int rate, String author, String content, String serviceAddress) {
        this.productId = productId;
        this.recommendationId = recommendationId;
        this.rate = rate;
        this.author = author;
        this.content = content;
        this.serviceAddress = serviceAddress;
    }

    public int getProductId() {
        return productId;
    }

    public int getRecommendationId() {
        return recommendationId;
    }

    public int getRate() {
        return rate;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public String getServiceAddress() {
        return serviceAddress;
    }
}
