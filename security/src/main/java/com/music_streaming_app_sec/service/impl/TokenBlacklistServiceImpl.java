package com.music_streaming_app_sec.service.impl;

import com.music_streaming_app_sec.entity.BlockedTokens;
import com.music_streaming_app_sec.repository.TokenBlacklistRepository;
import com.music_streaming_app_sec.service.TokenBlacklistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TokenBlacklistServiceImpl implements TokenBlacklistService {

    private final TokenBlacklistRepository repository;

    @Override
    public void addToBlackList(String token) {
        repository.save(new BlockedTokens(token));
    }

    @Override
    public boolean isBlacklisted(String token) {
        Optional<BlockedTokens> tokenOpt = repository.findByToken(token);
        return tokenOpt.isPresent();
    }
}
