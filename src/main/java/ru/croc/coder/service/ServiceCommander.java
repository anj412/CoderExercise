package ru.croc.coder.service;

import org.springframework.stereotype.Service;
import ru.croc.coder.domain.User;
import ru.croc.coder.school.pearsons.SchoolRank;

public interface ServiceCommander {

    default public boolean checkUserIsTeacher(User user) {
        return user != null && user.getSchoolRank() == SchoolRank.TEACHER;
    }

    default public String convertToSecure(String string) {
        return Integer.valueOf(string.hashCode()).toString();
    }
}
