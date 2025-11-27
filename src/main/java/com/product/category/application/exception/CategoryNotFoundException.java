package com.product.category.application.exception;

public class CategoryNotFoundException extends RuntimeException {

  public CategoryNotFoundException(String message) {
    super(message);
  }

  public CategoryNotFoundException() {
    super("Category not found");
  }
}
