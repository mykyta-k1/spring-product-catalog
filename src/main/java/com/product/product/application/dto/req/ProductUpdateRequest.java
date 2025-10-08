package com.product.product.application.dto.req;

import java.math.BigDecimal;
import java.util.UUID;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ProductUpdateRequest {

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
