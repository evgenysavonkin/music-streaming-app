package com.music_streaming_app_sec.service;

import com.music_streaming_app_sec.dto.DtoJwtResponse;
import com.music_streaming_app_sec.dto.DtoRefreshTokenRequest;
import com.music_streaming_app_sec.entity.RefreshToken;
import com.music_streaming_app_sec.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

public interface RefreshTokenService {
    @Transactional
    RefreshToken createRefreshToken(String username);

    RefreshToken findByToken(String token);

    RefreshToken verifyExpiration(RefreshToken token);

    @Transactional
    ResponseEntity<DtoJwtResponse> refreshToken(DtoRefreshTokenRequest request);

    @Transactional
    void delete(User user);
}
