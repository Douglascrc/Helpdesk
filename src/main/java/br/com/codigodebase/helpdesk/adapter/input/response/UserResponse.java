package br.com.codigodebase.helpdesk.adapter.input.response;

import java.util.UUID;

public record UserResponse(
        UUID id,
        String username,
        String name,
        String email
) {
}
