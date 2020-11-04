package ru.croc.coder.service;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import ru.croc.coder.domain.User;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserContext {
    private User currentUser;

    public User getCurrentUser() {
        return currentUser;
    }

    public UserContext setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
        return this;
    }
}
