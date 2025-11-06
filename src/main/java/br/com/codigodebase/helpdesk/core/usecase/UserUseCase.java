package br.com.codigodebase.helpdesk.core.usecase;

import br.com.codigodebase.helpdesk.core.domain.User;
import br.com.codigodebase.helpdesk.infrastructure.exception.AuthorizationException;
import br.com.codigodebase.helpdesk.port.input.UserInputPort;
import br.com.codigodebase.helpdesk.port.output.UserOutputPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
public class UserUseCase implements UserInputPort {

    private UserOutputPort userOutputPort;

    public UserUseCase(UserOutputPort userOutputPort) {
        this.userOutputPort = userOutputPort;
    }

    public User saveUser(User user) {
        if (user.getId() == null) {
            user.setId(UUID.randomUUID());
        }
        log.info("Saving  user: {}", user.getUsername());
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return userOutputPort.save(user);
    }

    public Page<User> listAllUsers(Pageable pg) {
        List<User> users = userOutputPort.findAll(pg).getContent();
        return new PageImpl<>(users, pg, users.size());
    }

    public User getById(UUID id) {
        return userOutputPort.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public void deleteById(UUID id) {
        User user = getById(id);
        user.setActive(false);
        userOutputPort.deleteById(id);
    }

    public User findByUsername(String username)
    {
        return userOutputPort.findByUsername(username);
    }
}