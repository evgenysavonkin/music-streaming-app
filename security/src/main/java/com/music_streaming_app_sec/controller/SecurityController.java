package com.music_streaming_app_sec.controller;

import com.music_streaming_app_sec.dto.DtoJwtResponse;
import com.music_streaming_app_sec.dto.DtoRefreshTokenRequest;
import com.music_streaming_app_sec.dto.DtoUserCredentialsRequest;
import com.music_streaming_app_sec.exceptions.ExceptionResponse;
import com.music_streaming_app_sec.exceptions.UnauthorizedException;
import com.music_streaming_app_sec.exceptions.UserAlreadyExistsException;
import com.music_streaming_app_sec.service.AuthenticationService;
import com.music_streaming_app_sec.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class SecurityController {

    private final AuthenticationService service;
    private final RefreshTokenService tokenService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody DtoUserCredentialsRequest request) {
        service.register(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<DtoJwtResponse> authenticate(@RequestBody DtoUserCredentialsRequest request) {
        return service.authenticate(request);
    }

    @PostMapping("/refresh")
    public ResponseEntity<DtoJwtResponse> refreshToken(@RequestBody DtoRefreshTokenRequest request) {
        return tokenService.refreshToken(request);
    }

    //This is also
    @GetMapping("/securedAdm")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public String securedAdminEndpoint() {
        return "this is secured method for Admin role";
    }

    //Not working one
    @GetMapping("/securedUser")
    @PreAuthorize("hasAnyRole('USER')")
    public String securedUserEndpoint() {
        return "this is secured method for User role";
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
