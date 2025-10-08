package com.product.product.application.mapper;

import com.product.category.application.dto.resp.CategoryShortResponse;
import com.product.category.domain.model.Category;
import com.product.category.infrastructure.view.CategoryShortView;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(source = "parent.id", target = "parentId")
    CategoryShortResponse categoryShortViewToCategoryShortResponse(CategoryShortView categoryShortView);

    @Mapping(source = "parent.id", target = "parentId")
    CategoryShortResponse categoryToCategoryShortResponse(Category category);
}
