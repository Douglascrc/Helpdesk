package br.com.codigodebase.helpdesk.port.input;

import br.com.codigodebase.helpdesk.core.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface UserInputPort {
    User saveUser(User user);
    Page<User> listAllUsers(Pageable pg);
    User getById(UUID id);
    void deleteById(UUID id);
    User findByUsername(String username);
}
