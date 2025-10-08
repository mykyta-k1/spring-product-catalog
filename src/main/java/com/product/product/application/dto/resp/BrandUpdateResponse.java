package com.product.product.application.dto.resp;

import java.util.UUID;

public record BrandUpdateResponse (
    UUID id,
    String name,
    String logoUrl,
    String bannerUrl
) { }
