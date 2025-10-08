package com.product.product.application.dto.resp;

import com.product.category.application.dto.resp.CategoryShortResponse;
import java.math.BigDecimal;
import lombok.Setter;

public record ProductDetailsResponse (
    String name,
    String slug,
    String imageUrl,
    String description,
    BigDecimal price,
    int stock,
    BrandShortResponse brand,
    CategoryShortResponse category,
    ProductTypeShortResponse productType,
    int weightGrams,
    int volumeMl,
    String series
) { }
