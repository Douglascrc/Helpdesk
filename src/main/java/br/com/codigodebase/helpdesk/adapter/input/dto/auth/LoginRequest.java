package br.com.codigodebase.helpdesk.adapter.input.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginRequest {
    String username;
    String password;

}
