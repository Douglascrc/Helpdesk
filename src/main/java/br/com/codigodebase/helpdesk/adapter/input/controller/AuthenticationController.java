package br.com.codigodebase.helpdesk.adapter.input.controller;

import br.com.codigodebase.helpdesk.adapter.input.dto.auth.AuthResponse;
import br.com.codigodebase.helpdesk.adapter.input.dto.auth.LoginRequest;
import br.com.codigodebase.helpdesk.infrastructure.security.CustomUserDetails;
import br.com.codigodebase.helpdesk.infrastructure.security.JwtUtil;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@OpenAPIDefinition
@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/auth")
public class AuthenticationController {


    private final JwtUtil jwtUtil;

    private final AuthenticationManager authManager;

    @Operation(description = "This method get a bearer token to be used in the system")
    @PostMapping(value = "/token")
    public ResponseEntity doLogin(@RequestBody LoginRequest request) {
        try {
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );

            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            AuthResponse tokenResponse = jwtUtil.generateToken(userDetails.getUsername());

            return ResponseEntity.ok().body(tokenResponse);
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
