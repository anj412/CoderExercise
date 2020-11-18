package ru.croc.coder.controller.dto;

import java.util.Map;

public class ExceptionDto {
    private String type;
    private String message;
    private Map<String,String> more;

    public ExceptionDto(String type, String message, Map<String, String> more) {
        this.type = type;
        this.message = message;
        this.more = more;
    }

    public ExceptionDto() {
    }

    public String getType() {
        return type;
    }

    public ExceptionDto setType(String type) {
        this.type = type;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ExceptionDto setMessage(String message) {
        this.message = message;
        return this;
    }

    public Map<String, String> getMore() {
        return more;
    }

    public ExceptionDto setMore(Map<String, String> more) {
        this.more = more;
        return this;
    }


}
