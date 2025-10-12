package com.product.product.infrastructure.dao;

import com.product.product.domain.model.Product;
import com.product.product.infrastructure.view.ProductShortView;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductRepository extends JpaRepository<Product, UUID>,
    JpaSpecificationExecutor<Product> {

    List<ProductShortView> findFirst5ByOrderByCreatedAtDesc();

    Optional<Product> findBySlug(String slug);

    void deleteBySlug(String slug);

    boolean existsBySlug(String slug);
}
