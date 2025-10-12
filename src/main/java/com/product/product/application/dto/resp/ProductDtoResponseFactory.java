package com.product.product.application.dto.resp;

import com.product.category.application.dto.resp.CategoryShortResponse;
import com.product.product.application.dto.resp.BrandDtoResponseFactory.BrandShortResponse;
import com.product.product.application.dto.resp.ProductTypeDtoResponseFactory.ProductTypeShortResponse;
import java.math.BigDecimal;
import java.util.UUID;

public final class ProductDtoResponseFactory {

    /**
     * Призначення: DTO для повернення оновленої сутності
     */
    public record ProductUpdateResponse (
        UUID id,
        String name,
        String slug,
        String description,
        UUID brand,
        UUID category,
        UUID productType,
        BigDecimal price,
        int stock,
        int weightGrams,
        int volumeMl,
        String series
    ) { }

    /**
     * Призначення: DTO для повернення мінімальної інформації про продукт, яка необхідна для
     * візуальної ідентифікації
     */
    public record ProductShortResponse (
        UUID id,
        String name,
        String slug,
        String imageUrl,
        BigDecimal price
    ) { }

    /**
     * Призначення: DTO для повернення детальної інформації про продукт для сторінки з деталями продукту
     */
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
}
