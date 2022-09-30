package br.com.sharetips.entities.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class UserRegisterRequestDTO {
    @NotEmpty
    public String name;
    @Email
    public String email;
    @Size(min = 8)
    public String password;
}
