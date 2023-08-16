package dev.vuphatdat.service.impl;


import dev.vuphatdat.dto.UserDto;
import dev.vuphatdat.models.Role;
import dev.vuphatdat.models.User;
import dev.vuphatdat.repository.RoleRepository;
import dev.vuphatdat.repository.UserRepository;
import dev.vuphatdat.service.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.net.PasswordAuthentication;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Handler;
import java.util.stream.Collectors;


@Data
@Service
public class UserServiceImpl implements UserService {

     private UserRepository userRepository;
     private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    // get user from registration form
    @Override
    public void saveUser(UserDto userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setLastName(userDto.getLastName());
        user.setFirstName(userDto.getFirstName());
        Role role = roleRepository.findByName("ROLE_USER");
        if(role==null){
            role = checkRoleExit();
        }
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }


    // find  user by username
    @Override
    public User findByUsername(String username) {

        return userRepository.findByUsername(username);
    }


    // find all user
    @Override
    public List<UserDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map((user) -> mapToUserDto(user)).collect(Collectors.toList());
    }

    private UserDto mapToUserDto(User user){
        UserDto userDto = new UserDto();
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setUsername(user.getUsername());
        return userDto;
    }

    private Role checkRoleExit(){
        Role role = new Role();
        role.setName("ROLE_USER");
        return  roleRepository.save(role);
    }
}
