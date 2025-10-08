package com.product.product.application.service.impl;

import static com.product.util.SlugGenerator.generateSlug;

import com.product.product.application.dto.resp.ProductTypeShortResponse;
import com.product.product.application.dto.resp.ProductTypeUpdateResponse;
import com.product.product.application.exception.ProductTypeNotFoundException;
import com.product.product.application.mapper.ProductTypeMapper;
import com.product.product.application.service.contract.ProductTypeService;
import com.product.product.domain.model.ProductType;
import com.product.product.infrastructure.dao.ProductTypeRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductTypeServiceImpl implements ProductTypeService {

    private final ProductTypeRepository productTypeRepository;
    private final ProductTypeMapper productTypeMapper;

    @Override
    public ProductType findById(UUID id) {
        return productTypeRepository.findById(id).orElseThrow(ProductTypeNotFoundException::new);
    }

    @Transactional
    @Override
    public void save(String name) {
        productTypeRepository.save(ProductType.builder()
            .name(name)
                .slug(generateSlug(name, UUID.randomUUID()))
            .build()
        );
    }

    @Transactional
    @Override
    public ProductTypeUpdateResponse update(String slug, String name) {
        ProductType p = findBySlug(slug);

        if (!p.getName().equals(name)) {
            p.setName(name);
            generateSlug(name, UUID.randomUUID());
        }

        return productTypeMapper.productTypeToProductTypeUpdateResponse(p);
    }

    @Transactional
    @Override
    public void deleteBySlug(String slug) {
        productTypeRepository.deleteBySlug(slug);
    }

    private ProductType findBySlug(String slug) {
        return productTypeRepository.findBySlug(slug)
            .orElseThrow(ProductTypeNotFoundException::new);
    }

    @Override
    public List<ProductTypeShortResponse> findAllShortProductTypes() {
        return productTypeRepository.findAll().stream()
            .map(productTypeMapper::productTypeToProductTypeShortResponse).toList();
    }
}
