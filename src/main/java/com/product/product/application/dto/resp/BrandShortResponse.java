package com.product.product.application.dto.resp;

import java.util.UUID;

public record BrandShortResponse (
    UUID id,
    String name,
    String slug,
    String logoUrl,
    String bannerUrl
) { }
