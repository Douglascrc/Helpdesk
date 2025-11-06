package br.com.codigodebase.helpdesk.port.output;


import br.com.codigodebase.helpdesk.core.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface UserOutputPort {
    User save(User user);
    Page<User>findAll(Pageable pg);
    Optional<User> findById(UUID id);
    void deleteById(UUID id);
    User findByUsername(String username);
}
