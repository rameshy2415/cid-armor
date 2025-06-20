package com.hackathon.repository;

import com.hackathon.entity.TokenMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenMappingRepository extends JpaRepository<TokenMapping, Long> {
    List<TokenMapping> findByCustomerId(Long customerId);
    Optional<TokenMapping> findByTokenValue(String tokenValue);
    List<TokenMapping> findByFieldName(String fieldName);
}
