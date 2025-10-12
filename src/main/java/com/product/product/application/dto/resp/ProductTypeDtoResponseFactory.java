package com.product.product.application.dto.resp;

import java.util.UUID;

public final class ProductTypeDtoResponseFactory {

    /**
     * Призначення: DTO типу продукту, представлення якого повертається в мінімальному
     * наборі значень
     */
    public record ProductTypeShortResponse (
        UUID id,
        String name,
        String slug
    ) { }

    /**
     * Призначення: DTO типу продукту що вертається після оновлення
     */
    public record ProductTypeUpdateResponse (
        String name,
        String slug
    ) { }
}
