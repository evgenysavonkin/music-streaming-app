package com.music_streaming_app_sec.service;

import com.music_streaming_app_sec.dto.DtoJwtResponse;
import com.music_streaming_app_sec.dto.DtoRefreshTokenRequest;
import com.music_streaming_app_sec.entity.RefreshToken;
import org.springframework.http.ResponseEntity;

public interface RefreshTokenService {
    RefreshToken createRefreshToken(String username);

    RefreshToken findByToken(String token);

    RefreshToken verifyExpiration(RefreshToken token);

    ResponseEntity<DtoJwtResponse> refreshToken(DtoRefreshTokenRequest request);
}
