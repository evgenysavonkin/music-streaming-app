package com.music_streaming_app_sec.service;

import com.music_streaming_app_sec.converter.UserConverter;
import com.music_streaming_app_sec.dto.DtoJwtResponse;
import com.music_streaming_app_sec.dto.DtoUserCredentialsRequest;
import com.music_streaming_app_sec.entity.RefreshToken;
import com.music_streaming_app_sec.entity.User;
import com.music_streaming_app_sec.exceptions.UnauthorizedException;
import com.music_streaming_app_sec.exceptions.UserAlreadyExistsException;
import com.music_streaming_app_sec.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private final UserConverter userConverter;
    private final RefreshTokenService refreshTokenService;

    @Transactional
    public void register(DtoUserCredentialsRequest registerRequest) {
        if (userService.isUserExistsByEmail(registerRequest.getEmail())) {
            throw new UserAlreadyExistsException(registerRequest.getEmail());
        }
        User user = userConverter.toUser(registerRequest.getEmail(),
                passwordEncoder.encode(registerRequest.getPassword()));
        userService.save(user);
    }


    public ResponseEntity<DtoJwtResponse> authenticate(DtoUserCredentialsRequest authRequest) {
        User user = (User) userService.findByEmail(authRequest.getEmail());
        if (passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getEmail(),
                            authRequest.getPassword()
                    )
            );
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(authRequest.getEmail());

            String jwtToken = jwtUtils.generateToken(user);
            return ResponseEntity.ok()
                    .header("Authorization", "Bearer " + jwtToken)
                    .body(new DtoJwtResponse(jwtToken, refreshToken.getToken()));
        } else {
            throw new UnauthorizedException("Invalid credentials");
        }
    }
}
