package br.com.codigodebase.helpdesk.adapter.input.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
    String username;
    String accessToken;
    Long expiresIn;

}
