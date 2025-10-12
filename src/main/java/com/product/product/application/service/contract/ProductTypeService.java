package com.product.product.application.service.contract;

import com.product.product.application.dto.resp.ProductTypeDtoResponseFactory.ProductTypeShortResponse;
import com.product.product.domain.model.ProductType;
import com.product.product.application.dto.resp.ProductTypeDtoResponseFactory.ProductTypeUpdateResponse;
import java.util.List;
import java.util.UUID;

public interface ProductTypeService {

    ProductType findById(UUID id);

    void save(String name);

    ProductTypeUpdateResponse update(String slug, String name);

    void deleteBySlug(String slug);

    List<ProductTypeShortResponse> findAllShortProductTypes();
}
