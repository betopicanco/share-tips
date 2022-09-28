package br.com.sharetips.entities.dto.tip;

import br.com.sharetips.entities.Tip;
import br.com.sharetips.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class TipDTO {
    private Long authorId;
    private String title;
    private String content;

    public Tip toTip(User author) {
        return new Tip(null, getTitle(), getContent(), new Date(), author);
    }
}
