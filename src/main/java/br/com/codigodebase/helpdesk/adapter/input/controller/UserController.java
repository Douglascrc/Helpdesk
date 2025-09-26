package br.com.codigodebase.helpdesk.adapter.input.controller;

import br.com.codigodebase.helpdesk.adapter.input.mapper.UserMapper;
import br.com.codigodebase.helpdesk.adapter.input.dto.user.UserRequest;
import br.com.codigodebase.helpdesk.adapter.input.dto.user.UserResponse;
import br.com.codigodebase.helpdesk.core.domain.User;
import br.com.codigodebase.helpdesk.port.input.UserInputPort;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

import java.util.List;
import java.util.UUID;

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
        User newUser = userInputPort.saveUser(user);
        UserResponse response = mapper.toResponse(newUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "List all users with pagination")
    @GetMapping
    public ResponseEntity<List<UserResponse>> listAll(@PageableDefault(size = 10, page = 0) Pageable pg) {
        Page<User> users = userInputPort.listAllUsers(pg);
        List<UserResponse> response = users.map(mapper::toResponse).getContent();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Get user by ID")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable UUID id) {
        User user = userInputPort.getById(id);
        UserResponse response = mapper.toResponse(user);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Delete user by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable UUID id) {
        User user = userInputPort.getById(id);
        userInputPort.deleteById(user.getId());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "Update user by ID")
    @PatchMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable UUID id, @RequestBody UserRequest userRequest) {
        User existingUser = userInputPort.getById(id);
        User updatedUser = mapper.toUser(userRequest);


        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setPassword(updatedUser.getPassword());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setName(updatedUser.getName());

        User savedUser = userInputPort.saveUser(existingUser);
        UserResponse response = mapper.toResponse(savedUser);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}