package com.posgrado.ecommerce.exception;

import com.posgrado.ecommerce.exception.response.ErrorResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {
  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleEntityNotFound(Exception e){
       HttpStatus status = HttpStatus.NOT_FOUND;
       ErrorResponse errorResponse =
            ErrorResponse.builder()
           .code(status.value())
           .error(status.name())
           .message(e.getMessage())
           .build();
       return ResponseEntity.status(status).body(errorResponse);
     }

  @ExceptionHandler(RoleAlreadyTaken.class)
  public ResponseEntity<ErrorResponse> handleRoleAlreadyTaken(Exception e){
    HttpStatus status = HttpStatus.CONFLICT;
    ErrorResponse errorResponse =
        ErrorResponse.builder()
            .code(status.value())
            .error(status.name())
            .message(e.getMessage())
            .build();
    return ResponseEntity.status(status).body(errorResponse);
  }
  @ExceptionHandler(ProductNotFound.class)
  public ResponseEntity<ErrorResponse> handleProductNotFound(Exception e){
    HttpStatus status = HttpStatus.NOT_FOUND;
    ErrorResponse errorResponse =
        ErrorResponse.builder()
            .code(status.value())
            .error(status.name())
            .message(e.getMessage())
            .build();
    return ResponseEntity.status(status).body(errorResponse);
  }

  @ExceptionHandler(CategoryNotFound.class)
  public ResponseEntity<ErrorResponse> handleCategoryNotFound(Exception e){
    HttpStatus status = HttpStatus.NOT_FOUND;
    ErrorResponse errorResponse =
        ErrorResponse.builder()
            .code(status.value())
            .error(status.name())
            .message(e.getMessage())
            .build();
    return ResponseEntity.status(status).body(errorResponse);
  }
}
