package com.product.product.application.service.contract;

import com.product.product.application.dto.req.BrandCreateRequest;
import com.product.product.application.dto.req.BrandUpdateRequest;
import com.product.product.application.dto.resp.BrandShortResponse;
import com.product.product.application.dto.resp.BrandUpdateResponse;
import com.product.product.domain.model.Brand;
import java.util.List;
import java.util.UUID;

public interface BrandService {

    Brand findById(UUID id);

    void save(BrandCreateRequest dto);

    BrandUpdateResponse update(String slug, BrandUpdateRequest brandUpdateRequest);

    void deleteBySlug(String slug);

    List<BrandShortResponse> findAllShortBrands();
}
