package com.product.product.application.dto.req;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Getter;

public final class BrandDtoRequestFactory {

  /**
   * Призначення: DTO для створення брендів
   */
  @Getter
  public static class BrandCreateRequest {

    @NotBlank(message = "Field brand name, cannot be empty")
    @Size(max = 255, message = "Brand name must not exceed 255 characters")
    private String name;

    @Size(max = 255, message = "Logo url must not exceed 255 characters")
    private String logoUrl;

    @Size(max = 255, message = "Banner url must not exceed 255 characters")
    private String bannerUrl;
  }

  /**
   * Призначення: DTO для оновлення даних про бренд
   */
  @Getter
  public static class BrandUpdateRequest {

    @NotBlank(message = "Field brand name, cannot be empty")
    @Size(max = 255, message = "Brand name must not exceed 255 characters")
    private String name;

    @Size(max = 255, message = "Logo url must not exceed 255 characters")
    private String logoUrl;

    @Size(max = 255, message = "Banner url must not exceed 255 characters")
    private String bannerUrl;
  }
}
