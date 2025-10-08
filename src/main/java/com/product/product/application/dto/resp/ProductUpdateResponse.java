package com.product.product.application.dto.resp;

import java.math.BigDecimal;
import java.util.UUID;

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
