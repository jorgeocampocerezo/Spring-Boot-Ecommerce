package com.posgrado.ecommerce.service;

import com.posgrado.ecommerce.dto.RolDto;
import com.posgrado.ecommerce.entity.Role;
import com.posgrado.ecommerce.exception.RoleAlreadyTaken;
import com.posgrado.ecommerce.repository.RoleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {

  private RoleRepository roleRepository;

  @Override
  public Role getByName(String name) {
    return roleRepository.findByName(name)
        .orElseThrow(() -> new EntityNotFoundException("Role not found"));
  }

  @Override
  public Role save(RolDto dto) {

    boolean existsRole = roleRepository.existsByName(dto.getName());
    String id = dto.getId().toString();
    if(existsRole){
      throw new RoleAlreadyTaken(dto.getName());
    }
    Role role = new Role();
    role.setId(dto.getId());
    role.setName(dto.getName());
    role.setDescription(dto.getDescription());
    return roleRepository.save(role);
  }

  @Override
  public boolean existsRole(String roleName) {
    return roleRepository.existsByName(roleName);
  }
}
