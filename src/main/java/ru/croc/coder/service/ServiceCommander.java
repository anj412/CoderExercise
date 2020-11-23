package ru.croc.coder.service;

import org.springframework.stereotype.Service;
import ru.croc.coder.domain.User;
import ru.croc.coder.school.pearsons.SchoolRank;
import ru.croc.coder.service.exceptions.PermissionException;

public interface ServiceCommander {

    default public void checkUserIsTeacher(User user) {
        if (user == null || user.getSchoolRank() != SchoolRank.TEACHER)
            throw new PermissionException("Teacher be authorized");
    }

    default public String convertToSecure(String string) {
        return "hash" + Integer.valueOf(string.hashCode()).toString();
    }
}
