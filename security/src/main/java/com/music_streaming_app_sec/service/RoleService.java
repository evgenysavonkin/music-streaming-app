package com.music_streaming_app_sec.service;

import com.music_streaming_app_sec.entity.Role;

public interface RoleService {
    Role findByName(String name);
}
