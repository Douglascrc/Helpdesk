package br.com.codigodebase.helpdesk.adapter.output.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
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

}
