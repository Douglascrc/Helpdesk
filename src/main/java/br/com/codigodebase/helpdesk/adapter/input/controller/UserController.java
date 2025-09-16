package br.com.codigodebase.helpdesk.adapter.input.controller;

import br.com.codigodebase.helpdesk.adapter.input.mapper.UserMapper;
import br.com.codigodebase.helpdesk.adapter.input.dto.user.UserRequest;
import br.com.codigodebase.helpdesk.adapter.input.dto.user.UserResponse;
import br.com.codigodebase.helpdesk.core.domain.User;
import br.com.codigodebase.helpdesk.port.input.UserInputPort;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Users", description = "Operations related to users")
@OpenAPIDefinition
@RequestMapping("users")
@RestController
public class UserController {

    private final UserInputPort userInputPort;
    private final UserMapper mapper;

    public UserController(UserInputPort userInputPort, UserMapper mapper) {
        this.userInputPort = userInputPort;
        this.mapper = mapper;
    }

    @Operation(summary = "Create a new user")
    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody UserRequest userRequest) {
        User user = mapper.toUser(userRequest);
        User newUser = userInputPort.createUser(user);
        UserResponse response = mapper.toResponse(newUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}