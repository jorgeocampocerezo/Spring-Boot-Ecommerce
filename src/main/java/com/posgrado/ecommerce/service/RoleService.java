package com.posgrado.ecommerce.service;

import com.posgrado.ecommerce.dto.RolDto;
import com.posgrado.ecommerce.entity.Role;

public interface RoleService {

  Role getByName(String name);
  Role save(RolDto dto);
  boolean existsRole(String roleName);
}
