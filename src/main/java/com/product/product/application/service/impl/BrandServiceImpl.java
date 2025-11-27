package com.product.product.application.service.impl;

import static com.product.util.SlugGenerator.generateSlug;

import com.product.product.application.dto.req.BrandDtoRequestFactory.BrandCreateRequest;
import com.product.product.application.dto.req.BrandDtoRequestFactory.BrandUpdateRequest;
import com.product.product.application.dto.resp.BrandDtoResponseFactory.BrandShortResponse;
import com.product.product.application.dto.resp.BrandDtoResponseFactory.BrandUpdateResponse;
import com.product.product.application.exception.BrandNotFoundException;
import com.product.product.application.mapper.BrandMapper;
import com.product.product.application.service.contract.BrandService;
import com.product.product.domain.model.Brand;
import com.product.product.infrastructure.dao.BrandRepository;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BrandServiceImpl implements BrandService {

  private final BrandRepository brandRepository;
  private final BrandMapper brandMapper;

  @Override
  public Brand findById(UUID id) {
    return brandRepository.findById(id).orElseThrow(BrandNotFoundException::new);
  }

  @Transactional(readOnly = false)
  @Override
  public void save(BrandCreateRequest dto) {
    UUID id = UUID.randomUUID();
    brandRepository.save(Brand.builder()
        .id(id)
        .name(dto.getName())
        .slug(generateSlug(dto.getName(), id))
        .logoUrl(dto.getLogoUrl())
        .bannerUrl(dto.getBannerUrl())
        .build()
    );
  }

  @Transactional(readOnly = false)
  @Override
  public BrandUpdateResponse update(String slug, BrandUpdateRequest dto) {
    Brand b = findBySlug(slug);
    brandMapper.updateBrandFromDto(dto, b);

    if (!b.getName().equals(dto.getName())) {
      b.setSlug(generateSlug(dto.getName(), b.getId()));
    }

    return brandMapper.brandToBrandUpdateResponse(b);
  }

  @Transactional(readOnly = false)
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
