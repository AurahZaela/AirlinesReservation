package com.synergisticit.service;

import java.util.List;

import com.synergisticit.domain.Role;

public interface RoleService {
	Role saveRole(Role role);
    Role getRoleById(Long roleId);
    List<Role> getAllRoles();
    void deleteRoleById(Long roleId);
    boolean existById(Long roleId);

}
