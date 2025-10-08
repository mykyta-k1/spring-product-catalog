package com.product.product.application.service.impl;

import static com.product.util.SlugGenerator.generateSlug;

import com.product.product.application.dto.req.BrandCreateRequest;
import com.product.product.application.dto.req.BrandUpdateRequest;
import com.product.product.application.dto.resp.BrandShortResponse;
import com.product.product.application.dto.resp.BrandUpdateResponse;
import com.product.product.application.exception.BrandNotFoundException;
import com.product.product.application.mapper.BrandMapper;
import com.product.product.application.service.contract.BrandService;
import com.product.product.domain.model.Brand;
import com.product.product.infrastructure.dao.BrandRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;

    @Override
    public Brand findById(UUID id) {
        return brandRepository.findById(id).orElseThrow(BrandNotFoundException::new);
    }

    @Transactional
    @Override
    public void save(BrandCreateRequest dto) {
        brandRepository.save(Brand.builder()
            .name(dto.getName())
            .slug(generateSlug(dto.getName(), UUID.randomUUID()))
            .logoUrl(dto.getLogoUrl())
            .bannerUrl(dto.getBannerUrl())
            .build()
        );
    }

    @Transactional
    @Override
    public BrandUpdateResponse update(String slug, BrandUpdateRequest dto) {
        Brand b = findBySlug(slug);

        if (!b.getName().equals(dto.getName())) {
            b.setName(dto.getName());
            generateSlug(dto.getName(), UUID.randomUUID());
        }
        if (!b.getLogoUrl().equals(dto.getLogoUrl())) {
            b.setLogoUrl(dto.getLogoUrl());
        }
        if (!b.getBannerUrl().equals(dto.getBannerUrl())) {
            b.setBannerUrl(dto.getBannerUrl());
        }

        return brandMapper.brandToBrandUpdateResponse(b);
    }

    @Transactional
    @Override
    public void deleteBySlug(String slug) {
        brandRepository.deleteBySlug(slug);
    }

    private Brand findBySlug(String slug) {
        return brandRepository.findBySlug(slug).orElseThrow(BrandNotFoundException::new);
    }

    @Override
    public List<BrandShortResponse> findAllShortBrands() {
        return brandRepository.findAll().stream()
            .map(brandMapper::brandToBrandShortResponse).toList();
    }
}
