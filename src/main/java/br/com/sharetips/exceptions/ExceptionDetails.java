package br.com.sharetips.exceptions;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
public class ExceptionDetails {
    private Integer status;
    private String title;
    private String details;
    private String developerMessage;
    private LocalDateTime timestamp;
}
