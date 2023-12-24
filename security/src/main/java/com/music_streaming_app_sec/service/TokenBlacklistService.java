package com.music_streaming_app_sec.service;

public interface TokenBlacklistService {
    void addToBlackList(String token);

    boolean isBlacklisted(String token);
}
