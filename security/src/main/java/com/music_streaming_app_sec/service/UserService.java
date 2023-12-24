package com.music_streaming_app_sec.service;

import com.music_streaming_app_sec.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    UserDetails loadUserByUsername(String email);
    void save(User user);
    UserDetails findByEmail(String email);
    boolean isUserExistsByEmail(String email);
}
