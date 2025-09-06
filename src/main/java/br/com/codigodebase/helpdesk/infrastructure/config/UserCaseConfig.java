package br.com.codigodebase.helpdesk.infrastructure.config;

import br.com.codigodebase.helpdesk.core.usecase.UserUseCase;
import br.com.codigodebase.helpdesk.port.input.UserInputPort;
import br.com.codigodebase.helpdesk.port.output.UserOutputPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserCaseConfig {

    @Bean
    public UserInputPort userInputPort(UserOutputPort userOutputPort) {
        return new UserUseCase(userOutputPort);
    }
}
