package dev.vuphatdat.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class UserDto {
    private Long id;

    @NotEmpty
    private String username;

    @NotEmpty
    private String lastName;

    @NotEmpty
    private String firstName;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty

    private String password;

    @NotEmpty
    private String repeatPassword;
}
