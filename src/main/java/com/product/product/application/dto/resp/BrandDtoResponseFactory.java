package com.product.product.application.dto.resp;

import java.util.UUID;

public final class BrandDtoResponseFactory {

  /**
   * Призначення: DTO для повернення базової інформації про бренд
   */
  public record BrandShortResponse(
      UUID id,
      String name,
      String slug,
      String logoUrl,
      String bannerUrl
  ) {

  }

  /**
   * Призначення: DTO для повернення оновленого бренду
   */
  public record BrandUpdateResponse(
      UUID id,
      String name,
      String logoUrl,
      String bannerUrl
  ) {

  }
}
