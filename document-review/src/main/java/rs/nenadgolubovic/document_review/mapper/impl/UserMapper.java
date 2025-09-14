package mapper.impl;

import dto.UserDto;
import mapper.Mapper;
import model.User;

public class UserMapper implements Mapper<UserDto, User> {
    @Override
    public User toEntity(UserDto userDto) {
        return null;
    }

    @Override
    public UserDto toDto(User user) {
        return null;
    }
}
