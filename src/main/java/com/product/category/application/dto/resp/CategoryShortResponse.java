package com.product.category.application.dto.resp;

import java.util.UUID;

public record CategoryShortResponse(
    UUID id,
    String name,
    String slug,
    UUID parentId
) {

}
