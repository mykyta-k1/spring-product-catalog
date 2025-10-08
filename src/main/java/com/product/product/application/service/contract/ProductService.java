package com.product.product.application.service.contract;

import com.product.product.application.dto.req.ProductCreateRequest;
import com.product.product.application.dto.req.ProductFilterRequest;
import com.product.product.application.dto.req.ProductUpdateRequest;
import com.product.product.application.dto.resp.ProductDetailsResponse;
import com.product.product.application.dto.resp.ProductShortResponse;
import com.product.product.application.dto.resp.ProductUpdateResponse;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    ProductDetailsResponse findBySlug(String slug);

    void save(ProductCreateRequest dto);

    ProductUpdateResponse update(String slug, ProductUpdateRequest dto);

    void deleteBySlug(String slug);

    Page<ProductShortResponse> findAllBy(ProductFilterRequest filters, Pageable pageable);

    List<ProductShortResponse> findAllProductsByLatestCreatedAt();
}
