package br.com.sharetips.entities.dto;

import br.com.sharetips.entities.Tip;
import br.com.sharetips.entities.User;

import java.util.Date;

public class InsertTipDTO {
    private Long authorId;
    private String title;
    private String content;

    public InsertTipDTO() { }

    public InsertTipDTO(Long authorId, String title, String content) {
        this.authorId = authorId;
        this.title = title;
        this.content = content;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Tip toTip(User author) {
        Tip tip = new Tip(null, getTitle(), getContent(), new Date(), author);

        return tip;
    }
}
