package com.product.product.application.dto.req;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import lombok.Data;

@Data
public class ProductFilterRequest {
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
