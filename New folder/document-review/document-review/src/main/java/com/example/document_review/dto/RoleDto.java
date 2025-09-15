package com.example.document_review.dto;

import java.util.Objects;

public class RoleDto {

    private Integer roleId;
    private String roleName;

    public RoleDto(Integer roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public RoleDto() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleDto roleDto = (RoleDto) o;
        return Objects.equals(roleId, roleDto.roleId) && Objects.equals(roleName, roleDto.roleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, roleName);
    }

    @Override
    public String toString() {
        return "RoleDto{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                '}';
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
