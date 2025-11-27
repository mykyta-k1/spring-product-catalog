package com.product.product.infrastructure.dao;

import com.product.product.domain.model.ProductType;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductTypeRepository extends JpaRepository<ProductType, UUID> {

  Optional<ProductType> findBySlug(String slug);

  void deleteBySlug(String slug);
}
