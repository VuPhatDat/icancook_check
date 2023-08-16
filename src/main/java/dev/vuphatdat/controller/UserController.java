package dev.vuphatdat.controller;

import dev.vuphatdat.dto.UserDto;
import dev.vuphatdat.models.User;
import dev.vuphatdat.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
public class UserController {
    @Autowired private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }
    @GetMapping("/home")
    public String home(){
         return "home";
    }


    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto userDto,
                               BindingResult result,
                               Model model){
        User existingUser = userService.findByUsername(userDto.getUsername());
        if(existingUser != null && existingUser.getUsername()!= null && !existingUser.getUsername().isEmpty()){
            result.rejectValue("username", null,
                    "There is already an account registered with same USERNAME");
        }
        if(result.hasErrors()){
            model.addAttribute("user", userDto);
        }
        userService.saveUser(userDto);
        return "redirect:/login";
    }


    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/users")
    public String listRegisteredUsers(Model model){
        System.out.println(SecurityContextHolder.getContext().getAuthentication());
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }


   // @PreAuthorize("isAuthenticated()")
    @GetMapping("/index")
    public String goHomeUser(){
        return "index";
    }


}
