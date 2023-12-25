package com.music_streaming_app_sec.service.impl;

import com.music_streaming_app_sec.entity.Role;
import com.music_streaming_app_sec.repository.RoleRepository;
import com.music_streaming_app_sec.service.RoleService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository repository;

    @Override
    public Role findByName(String name) {
        Optional<Role> roleOptional = repository.findByName(name);
        return roleOptional.orElseThrow(() ->
                new EntityNotFoundException("Role with name = " + name + " not found"));
    }

    @Override
    public void save(Role role) {
        repository.save(role);
    }

    @Override
    public void delete(Role role) {
        repository.delete(role);
    }
}
