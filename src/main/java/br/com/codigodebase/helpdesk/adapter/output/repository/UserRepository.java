package br.com.codigodebase.helpdesk.adapter.output.repository;

import br.com.codigodebase.helpdesk.adapter.input.mapper.UserMapper;
import br.com.codigodebase.helpdesk.adapter.output.entity.UserEntity;
import br.com.codigodebase.helpdesk.core.domain.User;
import br.com.codigodebase.helpdesk.port.output.UserOutputPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Slf4j
public class UserRepository implements UserOutputPort {

    private final JdbcTemplate jdbcTemplate;

    private final UserMapper mapper;

    public UserRepository (JdbcTemplate jdbcTemplate, UserMapper mapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.mapper = mapper;
    }

    @Override
    public User save(User user) {
        jdbcTemplate.update("CALL pr_create_user(?, ?, ?, ?, ?)",
                user.getId().toString(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getName()
        );

        log.info("User saved with sucess: {}", user.getId());
        return user;
    }

    @Override
    public Optional<User> findById(UUID id) {
        try {
            UserEntity user = jdbcTemplate.queryForObject(
                    "SELECT * FROM users WHERE id = CAST(? AS uuid)",
                    new Object[]{id.toString()},
                    (rs, rowNum) -> new UserEntity(
                            UUID.fromString(rs.getString("id")),
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getString("email"),
                            rs.getString("name"),
                            rs.getBoolean("active")
                    )
            );
            log.info("User found with id: {}", id);
            return Optional.of(mapper.toDomain(user));
            } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
            }

    }

    @Override
    public Page<User> findAll(Pageable pg) {
        List<UserEntity> users = jdbcTemplate.query(
                "SELECT * FROM users LIMIT ? OFFSET ?",
                new Object[]{pg.getPageSize(), pg.getOffset()},
                (rs, rowNum) -> new UserEntity(
                        UUID.fromString(rs.getString("id")),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("name"),
                        rs.getBoolean("active")
                )
        );

        log.info("Users retrieved: {}", users.size());
        List<User> userList = users.stream().map(mapper::toDomain).toList();
        int total = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM users", Integer.class);

        return new PageImpl<User>(userList, pg, total);
    }

    @Override
    public void deleteById(UUID id) {
        Optional<User> user = findById(id);
        if(!user.get().isActive()) {
            throw new RuntimeException("User already inactive");
        }
        jdbcTemplate.update("UPDATE users SET active = false WHERE id = CAST(? AS uuid)", id.toString());
        log.info("User with id {} set to inactive", id);
    }

    public User findByUsername(String username) {
        try {
            UserEntity user = jdbcTemplate.queryForObject(
                    "SELECT * FROM users WHERE username = ?",
                    new Object[]{username},
                    (rs, rowNum) -> new UserEntity(
                            UUID.fromString(rs.getString("id")),
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getString("email"),
                            rs.getString("name"),
                            rs.getBoolean("active")
                    )
            );
            log.info("User found with username: {}", username);
            return mapper.toDomain(user);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
