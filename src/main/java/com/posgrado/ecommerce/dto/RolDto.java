package com.posgrado.ecommerce.dto;

import jakarta.persistence.Column;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class RolDto {
  private UUID id;
  private String name;
  private String description;
}
