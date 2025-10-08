package com.product.category.application.service.contract;

import com.product.category.domain.model.Category;
import com.product.category.application.dto.resp.CategoryShortResponse;
import java.util.List;
import java.util.UUID;

public interface CategoryService {

    Category findById(UUID id);

    List<CategoryShortResponse> findAllShortCategories();
}
