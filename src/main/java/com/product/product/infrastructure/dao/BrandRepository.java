package com.product.product.infrastructure.dao;

import com.product.product.domain.model.Brand;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, UUID> {

    Optional<Brand> findBySlug(String slug);

    void deleteBySlug(String slug);
}
