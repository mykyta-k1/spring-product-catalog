package com.product.product.application.dto.resp;

import java.util.UUID;

public record ProductTypeShortResponse (
    UUID id,
    String name,
    String slug
) { }
