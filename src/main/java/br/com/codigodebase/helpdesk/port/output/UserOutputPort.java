package br.com.codigodebase.helpdesk.port.output;

import br.com.codigodebase.helpdesk.core.domain.model.User;

public interface UserOutputPort {
    User save(User user);
}
