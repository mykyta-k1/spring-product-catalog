package com.product.product.application.mapper;

import com.product.product.application.dto.req.ProductDtoRequestFactory.ProductUpdateRequest;
import com.product.product.application.dto.resp.ProductDtoResponseFactory.ProductDetailsResponse;
import com.product.product.application.dto.resp.ProductDtoResponseFactory.ProductShortResponse;
import com.product.product.application.dto.resp.ProductDtoResponseFactory.ProductUpdateResponse;
import com.product.product.domain.model.Product;
import com.product.product.infrastructure.view.ProductShortView;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {BrandMapper.class, CategoryMapper.class,
    ProductTypeMapper.class})
public interface ProductMapper {

    /**
     * Оновлює існуючий об'єкт {@link Product}, з даними {@link ProductUpdateRequest}
     * @param dto дані що надходять на оновлення продукту
     * @param product продукт з бази даних
     */
    void updateProductFromDto(ProductUpdateRequest dto, @MappingTarget Product product);

    @Mapping(source = "brand.id", target = "brand")
    @Mapping(source = "category.id", target = "category")
    @Mapping(source = "productType.id", target = "productType")
    ProductUpdateResponse productToProductUpdateResponse(Product product);

    ProductShortResponse productShortViewToProductShortResponse(ProductShortView productShortView);

    ProductDetailsResponse productToProductDetailsResponse(Product product);

    ProductShortResponse productToProductShortResponse(Product product);
}
