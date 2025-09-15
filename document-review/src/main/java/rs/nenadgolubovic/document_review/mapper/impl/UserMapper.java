package rs.nenadgolubovic.document_review.mapper.impl;


import rs.nenadgolubovic.document_review.dto.UserDto;
import rs.nenadgolubovic.document_review.entity.User;
import rs.nenadgolubovic.document_review.mapper.Mapper;

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
