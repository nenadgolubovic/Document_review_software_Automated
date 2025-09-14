package mapper.impl;

import dto.RoleDto;
import mapper.Mapper;
import model.Role;

public class RoleMapper implements Mapper<RoleDto, Role> {
    @Override
    public Role toEntity(RoleDto roleDto) {
        return null;
    }

    @Override
    public RoleDto toDto(Role role) {
        return null;
    }
}
