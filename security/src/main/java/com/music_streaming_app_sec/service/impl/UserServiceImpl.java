package com.music_streaming_app_sec.service.impl;

import com.music_streaming_app_sec.entity.User;
import com.music_streaming_app_sec.repository.UserRepository;
import com.music_streaming_app_sec.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserDetails loadUserByUsername(String email) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        return userOpt.orElseThrow(() -> new UsernameNotFoundException("User with email = " + email + " not found"));
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public UserDetails findByEmail(String email) {
        return loadUserByUsername(email);
    }

    public boolean isUserExistsByEmail(String email) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        return userOpt.isPresent();
    }
}
