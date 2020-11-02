package ru.croc.coder.domain;

import ru.croc.coder.school.exercises.ProgrammingLanguage;

import javax.persistence.*;

@Embeddable
public class Code {

    @Column(nullable = false)
    @Basic(fetch = FetchType.LAZY)
    @Lob
    private String text;


    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProgrammingLanguage language;

    public String getText() {
        return text;
    }

    public Code setText(String text) {
        this.text = text;
        return this;
    }

    public ProgrammingLanguage getLanguage() {
        return language;
    }

    public Code setLanguage(ProgrammingLanguage language) {
        this.language = language;
        return this;
    }
}
