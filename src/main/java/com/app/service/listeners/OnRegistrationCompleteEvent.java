package com.app.service.listeners;

import com.app.model.dto.UserDto;
import com.app.model.user.User;
import org.springframework.context.ApplicationEvent;

public class OnRegistrationCompleteEvent extends ApplicationEvent {
    private User user;
    private String appURL;

    public OnRegistrationCompleteEvent(User user, String appURL) {
        super(user);
        this.user = user;
        this.appURL = appURL;
    }

    public User getUser() {
        return user;
    }

    public void setUser(UserDto userDto) {
        this.user = user;
    }

    public String getAppURL() {
        return appURL;
    }

    public void setAppURL(String appURL) {
        this.appURL = appURL;
    }
}

