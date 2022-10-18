package br.com.sharetips.entities.dto.tip;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TipFeedDTO {
    private Long id;
    private String title;
    private String content;
    private Data createdAt;
}
