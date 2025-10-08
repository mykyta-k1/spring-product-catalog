package com.product.category.infrastructure.dao;

import com.product.category.domain.model.Category;
import com.product.category.infrastructure.view.CategoryShortView;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends JpaRepository<Category, UUID> {

}
