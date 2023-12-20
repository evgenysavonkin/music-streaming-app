package com.music_streaming_app_sec.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DtoJwtResponse {
    private String accessToken;
    private String refreshToken;
}
