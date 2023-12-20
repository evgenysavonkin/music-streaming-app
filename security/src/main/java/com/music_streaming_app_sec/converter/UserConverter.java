package com.music_streaming_app_sec.converter;

import com.music_streaming_app_sec.entity.Role;
import com.music_streaming_app_sec.entity.User;
import com.music_streaming_app_sec.enums.ERole;
import org.springframework.stereotype.Component;
import java.util.Set;

@Component
public class UserConverter {

    public User toUser(String email, String password) {
        return User.builder()
                .email(email)
                .password(password)
                .roles(Set.of(new Role(ERole.ROLE_USER)))
                .build();
    }
}
