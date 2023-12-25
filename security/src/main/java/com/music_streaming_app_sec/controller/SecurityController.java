package com.music_streaming_app_sec.controller;

import com.music_streaming_app_sec.dto.DtoJwtResponse;
import com.music_streaming_app_sec.dto.DtoRefreshTokenRequest;
import com.music_streaming_app_sec.dto.DtoRoleActionsRequest;
import com.music_streaming_app_sec.dto.DtoUserCredentialsRequest;
import com.music_streaming_app_sec.exceptions.ExceptionResponse;
import com.music_streaming_app_sec.exceptions.UnauthorizedException;
import com.music_streaming_app_sec.exceptions.UserAlreadyExistsException;
import com.music_streaming_app_sec.service.AuthenticationService;
import com.music_streaming_app_sec.service.RefreshTokenService;
import com.music_streaming_app_sec.service.TokenBlacklistService;
import com.music_streaming_app_sec.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class SecurityController {

    private final AuthenticationService authenticationService;
    private final RefreshTokenService tokenService;
    private final TokenBlacklistService tokenBlacklistService;
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody DtoUserCredentialsRequest request) {
        return authenticationService.register(request);
    }

    @PostMapping("/login")
    public ResponseEntity<DtoJwtResponse> authenticate(@RequestBody DtoUserCredentialsRequest request) {
        return authenticationService.authenticate(request);
    }

    @PostMapping("/refresh")
    public ResponseEntity<DtoJwtResponse> refreshToken(@RequestBody DtoRefreshTokenRequest request) {
        return tokenService.refreshToken(request);
    }

    @PostMapping("/logout")
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    public ResponseEntity<?> logout(HttpServletRequest request, Authentication authentication) {
        return tokenBlacklistService.logout(request, authentication);
    }

    @PostMapping("/add_role")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<?> addRole(@RequestBody DtoRoleActionsRequest request) {
        return userService.addRole(request);
    }

    @PostMapping("/delete_role")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<?> deleteRole(@RequestBody DtoRoleActionsRequest request) {
        return userService.deleteRole(request);
    }

    @ExceptionHandler
    @ResponseBody
    public ResponseEntity<ExceptionResponse> userAlreadyExistsExceptionHandler(UserAlreadyExistsException e) {
        return new ResponseEntity<>(createExceptionResponse(e), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    @ResponseBody
    public ResponseEntity<ExceptionResponse> incorrectPasswordExceptionHandler(UnauthorizedException e) {
        return new ResponseEntity<>(createExceptionResponse(e), HttpStatus.UNAUTHORIZED);
    }

    private ExceptionResponse createExceptionResponse(Exception e) {
        return new ExceptionResponse(e.getMessage(), new Date());
    }
}
