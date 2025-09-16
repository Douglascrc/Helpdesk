package br.com.codigodebase.helpdesk.port.input;

import br.com.codigodebase.helpdesk.core.domain.User;

public interface UserInputPort {
    User createUser(User user);
}
