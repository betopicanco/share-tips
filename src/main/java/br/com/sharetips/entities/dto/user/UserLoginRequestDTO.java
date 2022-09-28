package br.com.sharetips.entities.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserLoginRequestDTO {
    private String email;
    private String password;
}
