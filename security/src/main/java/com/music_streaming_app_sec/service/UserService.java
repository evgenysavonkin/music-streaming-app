package com.music_streaming_app_sec.service;

import com.music_streaming_app_sec.dto.DtoRoleActionsRequest;
import com.music_streaming_app_sec.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

public interface UserService {
    UserDetails loadUserByUsername(String email);

    @Transactional
    void save(User user);

    boolean isUserExistsByEmail(String email);

    @Transactional
    ResponseEntity<?> addRole(DtoRoleActionsRequest request);

    @Transactional
    ResponseEntity<?> deleteRole(DtoRoleActionsRequest request);
}
