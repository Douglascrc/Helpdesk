package br.com.codigodebase.helpdesk.adapter.output.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    private UUID id;

    private String username;

    private String password;

    private String email;

    private String name;

    private boolean active;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private UUID createdBy;

    private UUID updatedBy;

    public UserEntity(UUID id, String username, String password, String email, String name, boolean active) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.active = active;
    }
}
