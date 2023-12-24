package com.music_streaming_app_sec.dto;

import com.music_streaming_app_sec.entity.Role;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class DtoUserCredentialsRequest {
    private String email;
    private String password;
    private Set<Role> roles;
}
