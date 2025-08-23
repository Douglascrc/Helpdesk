package br.com.codigodebase.helpdesk.adapter.output.repository.impl;

import br.com.codigodebase.helpdesk.adapter.output.repository.entity.UserEntity;
import br.com.codigodebase.helpdesk.adapter.output.repository.UserRepository;
import br.com.codigodebase.helpdesk.core.domain.model.User;
import br.com.codigodebase.helpdesk.port.output.UserOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class UserRepositoryImpl implements UserOutputPort {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public User save(User user) {
        return null;
    }

    //    @Override
//    public UserEntity save(UserEntity user) {
//        if (user.getId() == null) {
//            user.setId(UUID.randomUUID());
//            jdbcTemplate.update(
//                    "INSERT INTO users (id, username, password, email, name, active, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
//                    user.getId(), user.getUsername(), user.getPassword(), user.getEmail(), user.getName(), true, LocalDateTime.now(), LocalDateTime.now()
//            );
//
//        }else {
//            jdbcTemplate.update(
//              "UPDATE users SET username = ?, password = ?, email = ?, name = ?, updated_at = ? WHERE id = ?" ,
//              user.getUsername(), user.getPassword(), user.getEmail(), user.getName(), LocalDateTime.now(), user.getId()
//            );
//        }
//        return user;
//    }
//
//    @Override
//    public Optional<UserEntity> findById(UUID id) {
//        return Optional.empty();
//    }
//
//    @Override
//    public List<UserEntity> findAll() {
//        return List.of();
//    }
//
//    @Override
//    public void deleteById(UUID id) {
//
//    }
}
