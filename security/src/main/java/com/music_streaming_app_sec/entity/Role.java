package com.music_streaming_app_sec.entity;

import com.music_streaming_app_sec.enums.ERole;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue
    private long id;

    @Enumerated(EnumType.STRING)
    @NonNull
    private ERole name;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;
}
