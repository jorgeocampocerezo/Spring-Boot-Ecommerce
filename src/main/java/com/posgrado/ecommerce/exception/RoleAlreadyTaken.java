package com.posgrado.ecommerce.exception;

public class RoleAlreadyTaken extends RuntimeException{

  public final static String MESSAGE_ERROR = "Role %s is already taken";

  public RoleAlreadyTaken(String roleName) {
    super(String.format(MESSAGE_ERROR, roleName));
  }
}
