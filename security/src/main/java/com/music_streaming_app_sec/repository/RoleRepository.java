package com.music_streaming_app_sec.repository;

import com.music_streaming_app_sec.entity.Role;
import com.music_streaming_app_sec.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
