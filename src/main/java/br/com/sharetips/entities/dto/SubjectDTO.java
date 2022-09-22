package br.com.sharetips.entities.dto;

import br.com.sharetips.entities.Subject;

public class SubjectDTO {
    private String name;

    public SubjectDTO() {}

    public SubjectDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Subject toSubject() {
        return new Subject(null, getName());
    }
}
