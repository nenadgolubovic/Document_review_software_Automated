package com.example.document_review.service;

import com.example.document_review.dto.RoleDto;

public interface RoleService {
    void update(RoleDto roleDto);
    void create(RoleDto roleDto);
    void delete(RoleDto roleDto);
    void getAllRoles();
    void updateRolePermissions();
}
