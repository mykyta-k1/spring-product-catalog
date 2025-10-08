package com.product.product.application.service.impl;

import static com.product.util.SlugGenerator.generateSlug;

import com.product.category.application.service.contract.CategoryService;
import com.product.product.application.dto.req.ProductCreateRequest;
import com.product.product.application.dto.req.ProductFilterRequest;
import com.product.product.application.dto.req.ProductUpdateRequest;
import com.product.product.application.dto.resp.ProductDetailsResponse;
import com.product.product.application.dto.resp.ProductShortResponse;
import com.product.product.application.dto.resp.ProductUpdateResponse;
import com.product.product.application.exception.ProductNotFoundException;
import com.product.product.application.mapper.ProductMapper;
import com.product.product.application.service.contract.BrandService;
import com.product.product.application.service.contract.ProductService;
import com.product.product.application.service.contract.ProductTypeService;
import com.product.product.domain.model.Product;
import com.product.product.infrastructure.dao.ProductRepository;
import com.product.product.infrastructure.dao.ProductSpecifications;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
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

    @Transactional
    @Override
    public void save(ProductCreateRequest dto) {
        productRepository.save(Product.builder()
            .name(dto.getName())
            .description(dto.getDescription())
            .slug(generateSlug(dto.getName(), UUID.randomUUID()))
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
    }

    @Transactional
    @Override
    public ProductUpdateResponse update(String slug, ProductUpdateRequest dto) {
        Product p = findProductBySlug(slug);

        if (!p.getName().equals(dto.getName())) {
            p.setName(dto.getName());
            generateSlug(dto.getName(), UUID.randomUUID());
        }
        if (!p.getDescription().equals(dto.getDescription())) {
            p.setDescription(dto.getDescription());
        }
        if (!p.getPrice().equals(dto.getPrice())) {
            p.setPrice(dto.getPrice());
        }
        if (p.getStock() != dto.getStock()) {
            p.setStock(dto.getStock());
        }
        if (p.getBrand().getId().equals(dto.getBrandId())) {
            p.setBrand(brandService.findById(dto.getBrandId()));
        }
        if (p.getCategory().getId().equals(dto.getCategoryId())) {
            p.setCategory(categoryService.findById(dto.getCategoryId()));
        }
        if (p.getProductType().getId().equals(dto.getProductTypeId())) {
            p.setProductType(productTypeService.findById(dto.getProductTypeId()));
        }
        if (p.getWeightGrams() != dto.getWeightGrams()) {
            p.setWeightGrams(dto.getWeightGrams());
        }
        if (p.getVolumeMl() != dto.getVolumeMl()) {
            p.setVolumeMl(dto.getVolumeMl());
        }
        if (p.getSeries().equals(dto.getSeries())) {
            p.setSeries(dto.getSeries());
        }

        return productMapper.productToProductUpdateResponse(p);
    }

    @Transactional
    @Override
    public void deleteBySlug(String slug) {
        productRepository.deleteBySlug(slug);
    }

    private Product findProductBySlug(String slug) {
        return productRepository.findBySlug(slug).orElseThrow(ProductNotFoundException::new);
    }

    @Transactional
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

    @Transactional
    @Override
    public List<ProductShortResponse> findAllProductsByLatestCreatedAt() {
        return productRepository.findFirst5ByOrderByCreatedAtDesc().stream()
            .map(productMapper::productShortViewToProductShortResponse).toList();
    }
}
