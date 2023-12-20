package com.music_streaming_app_sec.service;

import com.music_streaming_app_sec.entity.Role;
import com.music_streaming_app_sec.enums.ERole;
import com.music_streaming_app_sec.repository.RoleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository repository;

    public Role findByName(ERole name) {
        Optional<Role> roleOptional = repository.findByName(name);
        return roleOptional.orElseThrow(() ->
                new EntityNotFoundException("Role with name = " + name.toString() + " not found"));
    }
}
