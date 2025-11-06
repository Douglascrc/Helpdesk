package br.com.codigodebase.helpdesk.infrastructure.config;

import br.com.codigodebase.helpdesk.core.domain.User;
import br.com.codigodebase.helpdesk.core.usecase.UserUseCase;
import br.com.codigodebase.helpdesk.infrastructure.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserUseCase userService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("Getting information for user {}", username);
        User user = userService.findByUsername(username);
        if (user != null) {
            logger.warn("Information for user {} found", username);
            return new CustomUserDetails(user);
        }
        logger.error("Could not find the user {}", username);
        throw new UsernameNotFoundException("Could not find user");
    }
}