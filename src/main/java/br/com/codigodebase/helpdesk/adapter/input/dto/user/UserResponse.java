package br.com.codigodebase.helpdesk.adapter.input.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserResponse {

    private UUID id;
    private String username;
    private String name;
    private String email;
}
