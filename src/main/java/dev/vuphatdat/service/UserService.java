package dev.vuphatdat.service;

import dev.vuphatdat.dto.UserDto;
import dev.vuphatdat.models.User;

import java.util.List;

public interface UserService {

    void saveUser(UserDto userDto);
    User findByUsername(String username);
    List<UserDto>   findAllUsers();
}
