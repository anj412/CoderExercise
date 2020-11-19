package ru.croc.coder.domain.stat;

public class ExToFile {
    private String description;
    private Integer intLevel;
    private Integer intLanguage;
    private String templateText;
    private Integer maxAttempts;

    public String getDescription() {
        return description;
    }

    public ExToFile setDescription(String description) {
        this.description = description;
        return this;
    }

    public Integer getIntLevel() {
        return intLevel;
    }

    public ExToFile setIntLevel(Integer intLevel) {
        this.intLevel = intLevel;
        return this;
    }

    public Integer getIntLanguage() {
        return intLanguage;
    }

    public ExToFile setIntLanguage(Integer intLanguage) {
        this.intLanguage = intLanguage;
        return this;
    }

    public String getTemplateText() {
        return templateText;
    }

    public ExToFile setTemplateText(String templateText) {
        this.templateText = templateText;
        return this;
    }

    public Integer getMaxAttempts() {
        return maxAttempts;
    }

    public ExToFile setMaxAttempts(Integer maxAttempts) {
        this.maxAttempts = maxAttempts;
        return this;
    }
}
