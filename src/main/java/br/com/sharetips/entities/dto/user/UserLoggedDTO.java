package br.com.sharetips.entities.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserLoggedDTO {
    private Long id;
    private String name;
    private String email;
    private String profession;
}
