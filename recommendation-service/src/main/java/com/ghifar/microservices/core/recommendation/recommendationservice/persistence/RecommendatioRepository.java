package com.ghifar.microservices.core.recommendation.recommendationservice.persistence;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecommendatioRepository extends CrudRepository<RecommendationEntity, String> {
    List<RecommendationEntity> findByProductId(int productId);
}
