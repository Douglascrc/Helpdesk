package br.com.codigodebase.helpdesk.adapter.input.controller;

import br.com.codigodebase.helpdesk.adapter.input.mapper.UserMapper;
import br.com.codigodebase.helpdesk.adapter.input.request.UserRequest;
import br.com.codigodebase.helpdesk.adapter.input.response.UserResponse;
import br.com.codigodebase.helpdesk.core.domain.model.User;
import br.com.codigodebase.helpdesk.port.input.UserInputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("users")
@RestController
public class UserController {

    @Autowired
    private UserInputPort userInputPort;

    @Autowired
    private UserMapper mapper;
    
    @PostMapping
    public ResponseEntity create(@RequestBody UserRequest userRequest) {
        User user = mapper.toUser(userRequest);
        User newUser = userInputPort.createUser(user);
        UserResponse response = mapper.toUserResponse(newUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}