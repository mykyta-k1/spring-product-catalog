package com.product.product.application.dto.req;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Getter;

public final class ProductDtoRequestFactory {

  /**
   * Призначення: DTO створення продукту
   */
  @Getter
  public static class ProductCreateRequest {

    @Size(max = 100, message = "Name must not exceed 100 characters")
    private String name;

    @Size(max = 5000, message = "Description must not exceed 5000 characters")
    private String description;

    @NotNull(message = "Branding to the product is mandatory")
    private UUID brandId;

    @NotNull(message = "Binding a category to a product is mandatory")
    private UUID categoryId;

    @NotNull(message = "Binding a type to a product is mandatory")
    private UUID productTypeId;

    @PositiveOrZero(message = "Price cannot be negative")
    private BigDecimal price;

    @PositiveOrZero(message = "Stock cannot be negative")
    private int stock;

    @PositiveOrZero(message = "Weight grams cannot be negative")
    private int weightGrams;

    @PositiveOrZero(message = "Volume ml, cannot be negative")
    private int volumeMl;

    @Size(max = 255, message = "Field series must not exceed 255 characters")
    private String series;
  }

  /**
   * Призначення: DTO оновлення продукту
   */
  @Getter
  public static class ProductUpdateRequest {

    @Size(max = 100, message = "Name must not exceed 100 characters")
    private String name;

    @Size(max = 5000, message = "Description must not exceed 5000 characters")
    private String description;

    @NotNull(message = "Branding to the product is mandatory")
    private UUID brandId;

    @NotNull(message = "Binding a category to a product is mandatory")
    private UUID categoryId;

    @NotNull(message = "Binding a type to a product is mandatory")
    private UUID productTypeId;

    @PositiveOrZero(message = "Price cannot be negative")
    private BigDecimal price;

    @PositiveOrZero(message = "Stock cannot be negative")
    private int stock;

    @PositiveOrZero(message = "Weight grams cannot be negative")
    private int weightGrams;

    @PositiveOrZero(message = "Volume ml, cannot be negative")
    private int volumeMl;

    @Size(max = 255, message = "Field series must not exceed 255 characters")
    private String series;
  }

  /**
   * Призначення: приймати вхідні параметри в форматі DTO від фільтра
   */
  @Getter
  public static class ProductFilterRequest {

    private List<UUID> categoryIds;
    private List<UUID> brandIds;
    private List<UUID> productTypeIds;
    private String keyword;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private Boolean inStock;
    private Integer minWeight;
    private Integer maxWeight;
    private Integer minVolume;
    private Integer maxVolume;
  }
}
