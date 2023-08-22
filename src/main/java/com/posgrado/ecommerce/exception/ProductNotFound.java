package com.posgrado.ecommerce.exception;

import java.util.UUID;

public class ProductNotFound extends RuntimeException {
  public final static String MESSAGE_ERROR = "Product %s not found";

  public ProductNotFound(UUID id) {
    super(String.format(MESSAGE_ERROR, id));
  }
}
