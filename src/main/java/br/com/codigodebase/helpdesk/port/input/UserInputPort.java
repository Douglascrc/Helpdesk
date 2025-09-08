package br.com.codigodebase.helpdesk.port.input;

import br.com.codigodebase.helpdesk.core.domain.model.User;

public interface UserInputPort {
    User createUser(User user);
}
