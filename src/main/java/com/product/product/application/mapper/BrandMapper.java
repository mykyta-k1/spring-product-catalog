package com.product.product.application.mapper;

import com.product.product.application.dto.resp.BrandShortResponse;
import com.product.product.application.dto.resp.BrandUpdateResponse;
import com.product.product.domain.model.Brand;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BrandMapper {

    BrandUpdateResponse brandToBrandUpdateResponse(Brand brand);

    BrandShortResponse brandToBrandShortResponse(Brand brand);
}
