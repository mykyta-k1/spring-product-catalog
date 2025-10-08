package com.product.product.application.dto.req;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Getter;

@Getter
public class BrandUpdateRequest {

    @NotBlank(message = "Field brand name, cannot be empty")
    @Size(max = 255, message = "Brand name must not exceed 255 characters")
    private String name;

    @Size(max = 255, message = "Logo url must not exceed 255 characters")
    private String logoUrl;

    @Size(max = 255, message = "Banner url must not exceed 255 characters")
    private String bannerUrl;
}
