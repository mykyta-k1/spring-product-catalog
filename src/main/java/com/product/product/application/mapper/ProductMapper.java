package com.product.product.application.mapper;

import com.product.product.application.dto.resp.ProductDetailsResponse;
import com.product.product.application.dto.resp.ProductShortResponse;
import com.product.product.application.dto.resp.ProductUpdateResponse;
import com.product.product.domain.model.Product;
import com.product.product.infrastructure.view.ProductShortView;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {BrandMapper.class, CategoryMapper.class,
    ProductTypeMapper.class})
public interface ProductMapper {

    @Mapping(source = "brand.id", target = "brand")
    @Mapping(source = "category.id", target = "category")
    @Mapping(source = "productType.id", target = "productType")
    ProductUpdateResponse productToProductUpdateResponse(Product product);

    ProductShortResponse productShortViewToProductShortResponse(ProductShortView productShortView);

    ProductDetailsResponse productToProductDetailsResponse(Product product);

    ProductShortResponse productToProductShortResponse(Product product);
}
