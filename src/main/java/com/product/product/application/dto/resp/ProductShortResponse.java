package com.product.product.application.dto.resp;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductShortResponse (
    UUID id,
    String name,
    String slug,
    String imageUrl,
    BigDecimal price
) { }
