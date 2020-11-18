package ru.croc.coder.controller.dto;

import ru.croc.coder.domain.UserCourseRegistration;
import ru.croc.coder.school.pearsons.SchoolRank;

import javax.persistence.*;
import java.util.Set;

public class UserDto {


    private Long id;

    private SchoolRank schoolRank;

    private String firstName;

    private String lastName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SchoolRank getSchoolRank() {
        return schoolRank;
    }

    public void setSchoolRank(SchoolRank schoolRank) {
        this.schoolRank = schoolRank;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
