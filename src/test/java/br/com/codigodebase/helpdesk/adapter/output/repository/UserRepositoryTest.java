package br.com.codigodebase.helpdesk.adapter.output.repository;

import br.com.codigodebase.helpdesk.adapter.input.mapper.UserMapper;
import br.com.codigodebase.helpdesk.adapter.output.entity.UserEntity;
import br.com.codigodebase.helpdesk.core.domain.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserRepositoryTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @Mock
    private UserMapper mapper;

    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository = new UserRepository(jdbcTemplate, mapper);
    }

    @BeforeAll
    static void createTestDomainUser() {
        testUser = new User();
        testUser.setId(UUID.randomUUID());
        testUser.setUsername("testtestUser");
        testUser.setPassword("password123");
        testUser.setEmail("test@example.com");
        testUser.setName("Test User");
        testUser.setActive(true);
    }

    private static User testUser;

    @Test
    @DisplayName("Should save user and return the saved user")
    void checkSavedUser() {
        User user = testUser;

        when(jdbcTemplate.update(
                eq("CALL pr_create_user(?, ?, ?, ?, ?)"),
                eq(user.getId().toString()),
                eq(user.getUsername()),
                eq(user.getPassword()),
                eq(user.getEmail()),
                eq(user.getName())
        )).thenReturn(1);

        User result = userRepository.save(user);


        assertEquals(user,result);
        verify(jdbcTemplate).update(
                eq("CALL pr_create_user(?, ?, ?, ?, ?)"),
                eq(user.getId().toString()),
                eq(user.getUsername()),
                eq(user.getPassword()),
                eq(user.getEmail()),
                eq(user.getName())
        );
    }

    @Test
    @DisplayName("Should find user by ID and return the user")
    void checkFindUserById() {
        UUID id = UUID.randomUUID();
        UserEntity userEntity = new UserEntity(id, "testuser", "password", "test@example.com", "Test User", true);
        User domainUser = new User();
        domainUser.setId(id);

       when(jdbcTemplate.queryForObject(
               eq("SELECT * FROM users WHERE id = CAST(? AS uuid)"),
               any(Object[].class),
               any(RowMapper.class))
       ).thenReturn(userEntity);

       when(mapper.toDomain(userEntity)).thenReturn(domainUser);

       Optional<User> result = userRepository.findById(id);

        assertTrue(result.isPresent());
        assertEquals(domainUser, result.get());
        verify(mapper).toDomain(userEntity);
    }

    @Test
    @DisplayName("Should return empty when user ID does not exist")
    void checkFindByNonExtistId() {
        UUID nonExistentId = UUID.randomUUID();

        when(jdbcTemplate.queryForObject(
                eq("SELECT * FROM users WHERE id = CAST(? AS uuid)"),
                any(Object[].class),
                any(RowMapper.class))
        ).thenThrow( new EmptyResultDataAccessException(1));

        Optional<User> foundUser = userRepository.findById(nonExistentId);
        assertTrue(foundUser.isEmpty());
    }

    @Test
    @DisplayName("Should return all users with pagination")
    void checkReturnAllUsers() {

        Pageable pageable = PageRequest.of(0,10);
        List<UserEntity> entities = List.of(
                new UserEntity(UUID.randomUUID(), "user1", "password1", "email1", "name'1", true),
                new UserEntity(UUID.randomUUID(), "user2", "password2", "email2", "name'2", true)
        );

        List<User> domainUsers = List.of(
                new User(), new User()
        );

        when(jdbcTemplate.query(
                eq("SELECT * FROM users LIMIT ? OFFSET ?"),
                any(Object[].class),
                any(RowMapper.class))
        ).thenReturn(entities);

        when(mapper.toDomain(entities.get(0))).thenReturn(domainUsers.get(0));
        when(mapper.toDomain(entities.get(1))).thenReturn(domainUsers.get(1));
        when(jdbcTemplate.queryForObject(eq("SELECT COUNT(*) FROM users"), eq(Integer.class))).thenReturn(5);

        Page<User> result = userRepository.findAll(pageable);

        assertEquals(2,result.getContent().size());
        assertEquals(2,result.getTotalElements());
        verify(mapper,times(2)).toDomain(any(UserEntity.class));
    }

    @Test
    @DisplayName("Should return paged users when findAll is called with pagination")
    void checkReturnUsersWithPagination() {

        for (int i = 1; i <= 5; i++) {
            userRepository.save(testUser);
            i++;
        }

        List<UserEntity> entities = List.of(
                new UserEntity(UUID.randomUUID(), "user1", "password1", "email1", "name1", true),
                new UserEntity(UUID.randomUUID(), "user2", "password2", "email2", "name2", true)
        );
        List<User> domainUsers = List.of(new User(), new User());

        when(jdbcTemplate.query(
                eq("SELECT * FROM users LIMIT ? OFFSET ?"),
                any(Object[].class),
                any(RowMapper.class))
        ).thenReturn(entities);

        when(mapper.toDomain(entities.get(0))).thenReturn(domainUsers.get(0));
        when(mapper.toDomain(entities.get(1))).thenReturn(domainUsers.get(1));


        when(jdbcTemplate.queryForObject(eq("SELECT COUNT(*) FROM users"), eq(Integer.class))).thenReturn(5);
        Page<User> userPage = userRepository.findAll(PageRequest.of(0, 2));

        assertEquals(2, userPage.getContent().size());
        assertEquals(5, userPage.getTotalElements());
        assertEquals(3, userPage.getTotalPages());
    }

    @Test
    @DisplayName("Should delete user by ID and verify user is inactive")
    void checkDeleteById() {

        User user = testUser;
        UUID userId = user.getId();

        when(jdbcTemplate.queryForObject(
                eq("SELECT * FROM users WHERE id = CAST(? AS uuid)"),
                any(Object[].class),
                any(RowMapper.class))
        ).thenReturn(new UserEntity(userId, user.getUsername(), user.getPassword(), user.getEmail(), user.getName(), true));
        when(mapper.toDomain(any(UserEntity.class))).thenReturn(user);

        when(jdbcTemplate.update(
                eq("UPDATE users SET active = false WHERE id = CAST(? AS uuid)"),
                eq(userId.toString())
        )).thenReturn(1);

        userRepository.deleteById(user.getId());

        when(jdbcTemplate.queryForObject(
                eq("SELECT * FROM users WHERE id = CAST(? AS uuid)"),
                any(Object[].class),
                any(RowMapper.class))
        ).thenReturn(new UserEntity(userId, user.getUsername(), user.getPassword(), user.getEmail(), user.getName(), false));

        User inactiveUser = new User();
        inactiveUser.setId(userId);
        inactiveUser.setActive(false);
        when(mapper.toDomain(any(UserEntity.class))).thenReturn(inactiveUser);

        Optional<User> deletedUser = userRepository.findById(userId);

        assertTrue(deletedUser.isPresent());
        assertFalse(deletedUser.get().isActive());
        verify(jdbcTemplate).update(
                eq("UPDATE users SET active = false WHERE id = CAST(? AS uuid)"),
                eq(userId.toString())
        );
    }

    @Test
    @DisplayName("Should return the updated user")
    void checkUpdatedUser() {
        User user = testUser;

        user.setName("Updated Name");
        user.setEmail("updated@example.com");

        when(jdbcTemplate.update(
                eq("CALL pr_create_user(?, ?, ?, ?, ?)"),
                anyString(), anyString(), anyString(), anyString(), anyString()
        )).thenReturn(1);

        userRepository.save(user);

        UserEntity updatedEntity = new UserEntity(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getName(),
                true
        );

        when(jdbcTemplate.queryForObject(
                eq("SELECT * FROM users WHERE id = CAST(? AS uuid)"),
                any(Object[].class),
                any(RowMapper.class))
        ).thenReturn(updatedEntity);


        when(mapper.toDomain(updatedEntity)).thenReturn(user);

        Optional<User> updatedUser = userRepository.findById(user.getId());

        assertTrue(updatedUser.isPresent());
        assertEquals("Updated Name", updatedUser.get().getName());
        assertEquals("updated@example.com", updatedUser.get().getEmail());
    }
}
