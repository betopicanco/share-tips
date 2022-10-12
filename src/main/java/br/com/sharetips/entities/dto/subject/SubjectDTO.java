package br.com.sharetips.entities.dto.subject;

import br.com.sharetips.entities.Subject;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
public class SubjectDTO {
    @NotEmpty
    private String name;
}
