package com.product.product.application.mapper;

import com.product.product.application.dto.resp.ProductTypeShortResponse;
import com.product.product.application.dto.resp.ProductTypeUpdateResponse;
import com.product.product.domain.model.ProductType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductTypeMapper {

    ProductTypeUpdateResponse productTypeToProductTypeUpdateResponse(ProductType productType);

    ProductTypeShortResponse productTypeToProductTypeShortResponse(ProductType productType);
}
