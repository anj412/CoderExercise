package ru.croc.coder.domain;

import ru.croc.coder.school.exercises.ProgrammingLanguage;

import javax.persistence.*;

@Embeddable
public class Code {

    @Column(nullable = false)
    @Basic(fetch = FetchType.LAZY)
    @Lob
    private String codeText;


    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProgrammingLanguage programmingLanguage;

    public String getCodeText() {
        return codeText;
    }

    public Code setCodeText(String codeText) {
        this.codeText = codeText;
        return this;
    }

    public ProgrammingLanguage getProgrammingLanguage() {
        return programmingLanguage;
    }
    public Code setProgrammingLanguage(ProgrammingLanguage programmingLanguage) {
        this.programmingLanguage = programmingLanguage;
        return this;
    }
}
