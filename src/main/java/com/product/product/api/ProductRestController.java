package com.product.product.api;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import com.product.product.application.dto.req.ProductDtoRequestFactory.ProductCreateRequest;
import com.product.product.application.dto.req.ProductDtoRequestFactory.ProductUpdateRequest;
import com.product.product.application.dto.resp.ProductDtoResponseFactory.ProductDetailsResponse;
import com.product.product.application.dto.resp.ProductDtoResponseFactory.ProductUpdateResponse;
import com.product.product.application.exception.ProductNotFoundDataException;
import com.product.product.application.service.contract.ProductService;
import jakarta.validation.Valid;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductRestController {

  private final ProductService productService;

  @GetMapping
  public ResponseEntity<Page<ProductDetailsResponse>> getAll(
      @RequestParam(required = false) String keyword,
      @RequestParam(required = false) BigDecimal minPrice,
      @RequestParam(required = false) BigDecimal maxPrice,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size
  ) {
    Page<ProductDetailsResponse> data = productService.findAllBy(
        keyword, minPrice, maxPrice, PageRequest.of(page, size));
    if (data.isEmpty()) {
      throw new ProductNotFoundDataException("Product list is empty");
    }
    return ResponseEntity.ok(data);
  }

  @GetMapping("/{slug}")
  public ResponseEntity<ProductDetailsResponse> get(@PathVariable String slug) {
    return ResponseEntity.ok(productService.findBySlug(slug));
  }

  @PostMapping
  public ResponseEntity<ProductUpdateResponse> create(
      @Valid @RequestBody ProductCreateRequest dto
  ) {
    return ResponseEntity.status(CREATED).body(productService.save(dto));
  }

  @PutMapping("/{slug}")
  public ResponseEntity<ProductUpdateResponse> update(
      @PathVariable String slug, @Valid @RequestBody ProductUpdateRequest dto) {
    return ResponseEntity.ok().body(productService.update(slug, dto));
  }

  @DeleteMapping("/{slug}")
  public ResponseEntity<Void> delete(@PathVariable String slug) {
    productService.deleteBySlug(slug);
    return ResponseEntity.status(NO_CONTENT).build();
  }
}
