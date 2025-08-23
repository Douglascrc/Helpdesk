package br.com.codigodebase.helpdesk.port.output;

import br.com.codigodebase.helpdesk.core.domain.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserOutputPort {
    User save(User user);
    List<User> findAll();
    Optional<User> findById(UUID id);
    void deleteById(UUID id);
}
