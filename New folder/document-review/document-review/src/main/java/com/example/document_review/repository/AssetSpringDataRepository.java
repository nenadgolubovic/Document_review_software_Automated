package com.example.document_review.repository;

import com.example.document_review.entity.Asset;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssetSpringDataRepository extends JpaRepository<Asset, Integer> {
}
