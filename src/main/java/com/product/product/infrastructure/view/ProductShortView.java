package com.product.product.infrastructure.view;

import java.math.BigDecimal;

public interface ProductShortView {

  String getName();

  String getSlug();

  String getImageUrl();

  BigDecimal getPrice();
}
