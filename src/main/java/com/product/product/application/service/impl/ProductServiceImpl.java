package com.product.product.application.service.impl;

import static com.product.shared.exception.ExceptionControllerAdvice.exceptionWrapper;
import static com.product.util.SlugGenerator.generateSlug;

import com.product.category.application.service.contract.CategoryService;
import com.product.product.application.dto.req.ProductDtoRequestFactory.ProductCreateRequest;
import com.product.product.application.dto.req.ProductDtoRequestFactory.ProductFilterRequest;
import com.product.product.application.dto.req.ProductDtoRequestFactory.ProductUpdateRequest;
import com.product.product.application.dto.resp.ProductDtoResponseFactory.ProductDetailsResponse;
import com.product.product.application.dto.resp.ProductDtoResponseFactory.ProductShortResponse;
import com.product.product.application.dto.resp.ProductDtoResponseFactory.ProductUpdateResponse;
import com.product.product.application.exception.ProductNotFoundException;
import com.product.product.application.exception.ProductUnavailableException;
import com.product.product.application.mapper.ProductMapper;
import com.product.product.application.service.contract.BrandService;
import com.product.product.application.service.contract.ProductService;
import com.product.product.application.service.contract.ProductTypeService;
import com.product.product.domain.model.Product;
import com.product.product.infrastructure.dao.ProductRepository;
import com.product.product.infrastructure.dao.ProductSpecifications;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {

  private final ProductTypeService productTypeService;
  private final ProductRepository productRepository;
  private final CategoryService categoryService;
  private final ProductMapper productMapper;
  private final BrandService brandService;

  @Override
  public ProductDetailsResponse findBySlug(String slug) {
    return productMapper.productToProductDetailsResponse(findProductBySlug(slug));
  }

  @Transactional(readOnly = false)
  @Override
  public ProductUpdateResponse save(ProductCreateRequest dto) {
    UUID id = UUID.randomUUID();
    Product p = productRepository.save(Product.builder()
        .id(id)
        .name(dto.getName())
        .description(dto.getDescription())
        .slug(generateSlug(dto.getName(), id))
        .description(dto.getDescription())
        .price(dto.getPrice())
        .stock(dto.getStock())
        .brand(brandService.findById(dto.getBrandId()))
        .category(categoryService.findById(dto.getCategoryId()))
        .productType(productTypeService.findById(dto.getProductTypeId()))
        .weightGrams(dto.getWeightGrams())
        .volumeMl(dto.getVolumeMl())
        .series(dto.getSeries())
        .build()
    );
    return productMapper.productToProductUpdateResponse(p);
  }

  @Transactional(readOnly = false)
  @Override
  public ProductUpdateResponse update(String slug, ProductUpdateRequest dto) {
    Product p = exceptionWrapper(
        () -> findProductBySlug(slug),
        cause -> new ProductUnavailableException(
            "Access denied legally", "Product update error", cause)
    );

    if (!p.getName().equals(dto.getName())) {
      p.setName(dto.getName());
      generateSlug(dto.getName(), p.getId());
    }

    productMapper.updateProductFromDto(dto, p);

    if (!p.getBrand().getId().equals(dto.getBrandId())) {
      p.setBrand(brandService.findById(dto.getBrandId()));
    }
    if (!p.getCategory().getId().equals(dto.getCategoryId())) {
      p.setCategory(categoryService.findById(dto.getCategoryId()));
    }
    if (!p.getProductType().getId().equals(dto.getProductTypeId())) {
      p.setProductType(productTypeService.findById(dto.getProductTypeId()));
    }

    return productMapper.productToProductUpdateResponse(p);
  }

  @Transactional(readOnly = false)
  @Override
  public void deleteBySlug(String slug) {
    if (productRepository.existsBySlug(slug)) {
      productRepository.deleteBySlug(slug);
    }
    throw new ProductNotFoundException();
  }

  private Product findProductBySlug(String slug) {
    return productRepository.findBySlug(slug).orElseThrow(ProductNotFoundException::new);
  }

  @Override
  public Page<ProductShortResponse> findAllBy(ProductFilterRequest filters, Pageable pageable
  ) {
    Specification<Product> spec = Specification
        .where(ProductSpecifications.categoryIn(filters.getCategoryIds()))
        .and(ProductSpecifications.brandIn(filters.getBrandIds()))
        .and(ProductSpecifications.nameContains(filters.getKeyword()))
        .and(ProductSpecifications.productTypeIn(filters.getProductTypeIds()))
        .and(ProductSpecifications.priceBetween(filters.getMinPrice(), filters.getMaxPrice()))
        .and(
            ProductSpecifications.weightBetween(filters.getMinWeight(), filters.getMaxWeight()))
        .and(ProductSpecifications.volumeBetween(
            filters.getMinVolume(),
            filters.getMaxVolume()
        ));

    if (Boolean.TRUE.equals(filters.getInStock())) {
      spec = spec.and(ProductSpecifications.inStock());
    }

    return productRepository.findAll(spec, pageable)
        .map(productMapper::productToProductShortResponse);
  }

  @Override
  public Page<ProductDetailsResponse> findAllBy(
      String keyword, BigDecimal minPrice, BigDecimal maxPrice,
      Pageable pageable
  ) {
    Specification<Product> spec = Specification
        .where(ProductSpecifications.nameContains(keyword))
        .and(ProductSpecifications.priceBetween(minPrice, maxPrice));

    return productRepository.findAll(spec, pageable)
        .map(productMapper::productToProductDetailsResponse);
  }

  @Override
  public List<ProductShortResponse> findAllProductsByLatestCreatedAt() {
    return productRepository.findFirst5ByOrderByCreatedAtDesc().stream()
        .map(productMapper::productShortViewToProductShortResponse).toList();
  }
}
