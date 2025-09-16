package br.com.codigodebase.helpdesk.adapter.input.dto.user;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class UserResponse {

    private UUID id;
    private String username;
    private String name;
    private String email;
}
