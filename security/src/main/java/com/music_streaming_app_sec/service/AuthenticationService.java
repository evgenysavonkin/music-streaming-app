package com.music_streaming_app_sec.service;

import com.music_streaming_app_sec.dto.DtoJwtResponse;
import com.music_streaming_app_sec.dto.DtoUserCredentialsRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

public interface AuthenticationService {
    @Transactional
    void register(DtoUserCredentialsRequest registerRequest);

    ResponseEntity<DtoJwtResponse> authenticate(DtoUserCredentialsRequest authRequest);
}
