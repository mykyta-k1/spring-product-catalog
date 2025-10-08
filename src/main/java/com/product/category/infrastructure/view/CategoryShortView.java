package com.product.category.infrastructure.view;

import java.util.UUID;

public interface CategoryShortView {
    UUID getId();
    String getName();
    String getSlug();
    CategoryShortView getParent();
}
