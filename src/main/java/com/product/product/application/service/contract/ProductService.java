package com.product.product.application.service.contract;

import com.product.product.application.dto.req.ProductDtoRequestFactory.ProductCreateRequest;
import com.product.product.application.dto.req.ProductDtoRequestFactory.ProductFilterRequest;
import com.product.product.application.dto.req.ProductDtoRequestFactory.ProductUpdateRequest;
import com.product.product.application.dto.resp.ProductDtoResponseFactory.ProductDetailsResponse;
import com.product.product.application.dto.resp.ProductDtoResponseFactory.ProductShortResponse;
import com.product.product.application.dto.resp.ProductDtoResponseFactory.ProductUpdateResponse;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

  ProductDetailsResponse findBySlug(String slug);

  ProductUpdateResponse save(ProductCreateRequest dto);

  ProductUpdateResponse update(String slug, ProductUpdateRequest dto);

  void deleteBySlug(String slug);

  Page<ProductShortResponse> findAllBy(ProductFilterRequest filters, Pageable pageable);

  Page<ProductDetailsResponse> findAllBy(
      String keyword, BigDecimal minPrice, BigDecimal maxPrice, Pageable pageable);

  List<ProductShortResponse> findAllProductsByLatestCreatedAt();
}
