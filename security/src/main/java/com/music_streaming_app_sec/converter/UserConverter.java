package com.music_streaming_app_sec.converter;

import com.music_streaming_app_sec.entity.Role;
import com.music_streaming_app_sec.entity.User;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class UserConverter {
    public User toUser(String email, String password, Set<Role> roles) {
        return User.builder()
                .email(email)
                .password(password)
                .roles(roles)
                .build();
    }
}
