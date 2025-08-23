package br.com.codigodebase.helpdesk.core.usecase;

import br.com.codigodebase.helpdesk.core.domain.model.User;
import br.com.codigodebase.helpdesk.port.input.UserInputPort;
import br.com.codigodebase.helpdesk.port.output.UserOutputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserUseCase implements UserInputPort {

    @Autowired
    private UserOutputPort userOutputPort;

    public User createUser(User user) {
        
        return userOutputPort.save(user);
    }
}
