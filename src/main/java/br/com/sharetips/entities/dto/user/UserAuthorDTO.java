package br.com.sharetips.entities.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
public class UserAuthorDTO {
    @NotEmpty
    private Long id;
    @NotEmpty
    private String email;
}
