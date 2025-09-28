package com.example.document_review.service.impl;
import com.example.document_review.dto.UserDto;
import com.example.document_review.entity.User;
import com.example.document_review.mapper.impl.UserMapper;
import com.example.document_review.repository.impl.UserRepository;
import com.example.document_review.service.UserService;
import com.example.document_review.validator.impl.RegistrationValidator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@Service
public class UserServiceImpl implements UserService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final RegistrationValidator registrationValidator;
    private final BCryptPasswordEncoder passwordEncoder;


    public UserServiceImpl(BCryptPasswordEncoder bCryptPasswordEncoder, UserMapper userMapper, UserRepository userRepository, RegistrationValidator registrationValidator, BCryptPasswordEncoder passwordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.registrationValidator = registrationValidator;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void register(UserDto userDto) {

        registrationValidator.validate(userDto);
        userDto.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        User user = userMapper.toEntity(userDto);
        userRepository.save(user);


    }

    @Override
    public UserDto findById(Integer id) {
        User user = userRepository.findById(id);
        return  userMapper.toDto(user);
    }


    public User loginUser(UserDto userDto) {

        User user = userRepository.findByUsername(userDto.getUsername());
        if (user == null || !passwordEncoder.matches(userDto.getPassword(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password");
        }


        return user;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
