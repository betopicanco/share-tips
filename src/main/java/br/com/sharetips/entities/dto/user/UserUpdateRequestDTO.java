package br.com.sharetips.entities.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequestDTO {
    @NotEmpty
    private String name;
    private String profession;
}
