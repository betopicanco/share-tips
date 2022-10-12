package br.com.sharetips.entities.dto.tip;

import br.com.sharetips.entities.Tip;
import br.com.sharetips.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
@AllArgsConstructor
public class TipCreateDTO {
    private Long authorId;
    @NotEmpty
    private String title;
    @NotEmpty
    private String content;

    public Tip toTip(User author) {
        return new Tip(null, getTitle(), getContent(), new Date(), author);
    }
}
