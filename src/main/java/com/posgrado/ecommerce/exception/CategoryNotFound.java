package com.posgrado.ecommerce.exception;

import java.util.UUID;

public class CategoryNotFound extends RuntimeException{

  public final static String MESSAGE_ERROR = "Category %s not found";

  public CategoryNotFound(UUID id) {
    super(String.format(MESSAGE_ERROR, id));
  }
}
