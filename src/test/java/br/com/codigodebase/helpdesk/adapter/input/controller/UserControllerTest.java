package br.com.codigodebase.helpdesk.adapter.input.controller;

import br.com.codigodebase.helpdesk.adapter.input.dto.user.UserRequest;
import br.com.codigodebase.helpdesk.adapter.input.dto.user.UserResponse;
import br.com.codigodebase.helpdesk.adapter.input.mapper.UserMapper;
import br.com.codigodebase.helpdesk.core.domain.User;
import br.com.codigodebase.helpdesk.port.input.UserInputPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class UserControllerTest {

    private UserInputPort userInputPort;
    private UserMapper mapper;
    private UserController controller;

    @BeforeEach
    void setUp() {
        userInputPort = mock(UserInputPort.class);
        mapper = mock(UserMapper.class);
        controller = new UserController(userInputPort, mapper);
    }

    @Test
    void testCreateUser() {

        UserRequest request = new UserRequest();
        User user = new User();
        User savedUser = new User();
        UserResponse response = new UserResponse();

        when(mapper.toUser(request)).thenReturn(user);
        when(userInputPort.saveUser(user)).thenReturn(savedUser);
        when(mapper.toResponse(savedUser)).thenReturn(response);

        ResponseEntity<UserResponse> result = controller.create(request);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(response, result.getBody());
        verify(mapper).toUser(request);
        verify(userInputPort).saveUser(user);
        verify(mapper).toResponse(savedUser);
    }

    @Test
    void testListAllUsers() {

        Pageable pageable = Pageable.ofSize(10);
        List<User> userList = List.of(new User(), new User());
        Page<User> userPage = new PageImpl<>(userList);
        UserResponse response1 = new UserResponse();
        UserResponse response2 = new UserResponse();
        List<UserResponse> responseList = List.of(response1, response2);

        when(userInputPort.listAllUsers(pageable)).thenReturn(userPage);
        when(mapper.toResponse(userList.get(0))).thenReturn(response1);
        when(mapper.toResponse(userList.get(1))).thenReturn(response2);

        ResponseEntity<List<UserResponse>> result = controller.listAll(pageable);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(responseList.size(), result.getBody().size());
        verify(userInputPort).listAllUsers(pageable);
        verify(mapper, times(2)).toResponse(any(User.class));
    }

    @Test
    void testGetUserById() {

        UUID id = UUID.randomUUID();
        User user = new User();
        UserResponse response = new UserResponse();

        when(userInputPort.getById(id)).thenReturn(user);
        when(mapper.toResponse(user)).thenReturn(response);

        ResponseEntity<UserResponse> result = controller.getById(id);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
        verify(userInputPort).getById(id);
        verify(mapper).toResponse(user);
    }

    @Test
    void testDeleteUserById() {

        UUID id = UUID.randomUUID();
        User user = new User();
        user.setId(id);

        when(userInputPort.getById(id)).thenReturn(user);
        doNothing().when(userInputPort).deleteById(id);

        ResponseEntity<String> result = controller.deleteById(id);

        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
        verify(userInputPort).getById(id);
        verify(userInputPort).deleteById(id);
    }

    @Test
    void testUpdateUser() {

        UUID id = UUID.randomUUID();
        UserRequest request = new UserRequest();
        User existingUser = new User();
        existingUser.setId(id);
        User updatedUserData = new User();
        updatedUserData.setUsername("newUsername");
        updatedUserData.setPassword("newPassword");
        updatedUserData.setEmail("new@email.com");
        updatedUserData.setName("New Name");
        User savedUser = new User();
        UserResponse response = new UserResponse();

        when(userInputPort.getById(id)).thenReturn(existingUser);
        when(mapper.toUser(request)).thenReturn(updatedUserData);
        when(userInputPort.saveUser(existingUser)).thenReturn(savedUser);
        when(mapper.toResponse(savedUser)).thenReturn(response);

        ResponseEntity<UserResponse> result = controller.updateUser(id, request);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
        verify(userInputPort).getById(id);
        verify(mapper).toUser(request);
        verify(userInputPort).saveUser(existingUser);
        verify(mapper).toResponse(savedUser);

        assertEquals(updatedUserData.getUsername(), existingUser.getUsername());
        assertEquals(updatedUserData.getPassword(), existingUser.getPassword());
        assertEquals(updatedUserData.getEmail(), existingUser.getEmail());
        assertEquals(updatedUserData.getName(), existingUser.getName());
    }
}