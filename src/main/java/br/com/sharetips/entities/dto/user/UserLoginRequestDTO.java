package br.com.sharetips.entities.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
public class UserLoginRequestDTO {
    @Email
    private String email;
    @NotEmpty
    private String password;
}
