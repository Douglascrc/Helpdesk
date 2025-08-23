package br.com.codigodebase.helpdesk.adapter.input.controller;

import br.com.codigodebase.helpdesk.adapter.input.request.UserRequest;
import br.com.codigodebase.helpdesk.core.domain.model.User;
import br.com.codigodebase.helpdesk.port.input.UserInputPort;
import org.springframework.beans.factory.annotation.Autowired;
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
    
    @PostMapping
    public ResponseEntity create(@RequestBody UserRequest userRequest) {
        User user = new User(userRequest.getName(), userRequest.getUsername(), userRequest.getEmail(), userRequest.getPassword());
        var newUser = userInputPort.createUser(user);
        
        return ResponseEntity.ok(newUser);
    }
}