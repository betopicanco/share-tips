package br.com.sharetips.entities.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
public class UserLoginRequestDTO {
    @NotEmpty
    private String email;
    @NotEmpty
    private String password;
}
