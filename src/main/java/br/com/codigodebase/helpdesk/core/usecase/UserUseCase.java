package br.com.codigodebase.helpdesk.core.usecase;

import br.com.codigodebase.helpdesk.core.domain.User;
import br.com.codigodebase.helpdesk.port.input.UserInputPort;
import br.com.codigodebase.helpdesk.port.output.UserOutputPort;

public class UserUseCase implements UserInputPort {

    private UserOutputPort userOutputPort;

    public UserUseCase(UserOutputPort userOutputPort) {
        this.userOutputPort = userOutputPort;
    }

    public User createUser(User user) {
        return userOutputPort.save(user);
    }
}