package com.product.product.application.mapper;


import com.product.product.application.dto.req.BrandDtoRequestFactory.BrandUpdateRequest;
import com.product.product.application.dto.resp.BrandDtoResponseFactory.BrandShortResponse;
import com.product.product.application.dto.resp.BrandDtoResponseFactory.BrandUpdateResponse;
import com.product.product.domain.model.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BrandMapper {

    /**
     * Оновлює наявний бренд з дто
     * @param dto дані дто
     * @param brand бренд з бази даних
     */
    void updateBrandFromDto(BrandUpdateRequest dto, @MappingTarget Brand brand);

    BrandUpdateResponse brandToBrandUpdateResponse(Brand brand);

    BrandShortResponse brandToBrandShortResponse(Brand brand);
}
