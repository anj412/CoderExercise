package ru.croc.coder.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import ru.croc.coder.domain.ProcessStatus;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SolutionDto {

    private Long id;

    private Long exerciseId;

    @JsonProperty("submitTime")
    private LocalDateTime time;


    private Boolean passed;

    private ProcessStatus checkStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(Long exerciseId) {
        this.exerciseId = exerciseId;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public Boolean getPassed() {
        return passed;
    }

    public void setPassed(Boolean passed) {
        this.passed = passed;
    }

    public ProcessStatus getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(ProcessStatus checkStatus) {
        this.checkStatus = checkStatus;
    }
}
