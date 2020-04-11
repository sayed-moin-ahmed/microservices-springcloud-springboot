package com.ghifar.microservices.composite.product.productcompositeservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ghifar.api.api.core.product.ProductService;
import com.ghifar.api.api.core.product.dto.Product;
import com.ghifar.api.api.core.recommendation.RecommendationService;
import com.ghifar.api.api.core.recommendation.dto.Recommendation;
import com.ghifar.api.api.core.review.ReviewService;
import com.ghifar.api.api.core.review.dto.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class ProductCompositeIntegration implements ProductService, RecommendationService, ReviewService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    private final String productServiceUrl;
    private final String recommendationServiceUrl;
    private final String reviewServiceUrl;

    @Autowired
    public ProductCompositeIntegration(RestTemplate restTemplate, ObjectMapper objectMapper,
                                       @Value("${app.product-service.host}") String productServiceHost,
                                       @Value("${app.product-service.port}")int productServicePort,

                                       @Value("${app.recommendation-service.host}") String recommendationServicHost,
                                       @Value("${app.product-service.port}") int recommendationServicePort,

                                       @Value("${app.product-review-service.host}")String reviewServiceHost,
                                       @Value("${app.product-review-service.port}")String reviewServicePort) {

        this.restTemplate= restTemplate;
        this.objectMapper= objectMapper;

        productServiceUrl        = "http://" + productServiceHost + ":" + productServicePort + "/product/";
        recommendationServiceUrl = "http://" + recommendationServicHost + ":" + recommendationServicePort + "/recommendation?productId=";
        reviewServiceUrl         = "http://" + reviewServiceHost + ":" + reviewServicePort + "/review?productId=";
    }

    @Override
    public Product getProduct(int productId) {
        String url= productServiceUrl+productId;
        Product product= restTemplate.getForObject(url,Product.class);
        return product;
    }

    @Override
    public List<Recommendation> getRecommendations(int productId) {
        String url= recommendationServiceUrl+productId;
        List<Recommendation>recommendations= restTemplate
                .exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Recommendation>>() {
                }).getBody();
        return recommendations;
    }

    @Override
    public List<Review> getReviews(int productId) {
        String url= reviewServiceUrl+productId;
        List<Review> reviews= restTemplate.exchange(url, HttpMethod.GET, null
                , new ParameterizedTypeReference<List<Review>>() {}).getBody();

        return reviews;
    }
}
