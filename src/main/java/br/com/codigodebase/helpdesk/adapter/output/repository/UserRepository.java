package br.com.codigodebase.helpdesk.adapter.output.repository;

import br.com.codigodebase.helpdesk.adapter.output.repository.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository {
    UserEntity save(UserEntity userEntity);

    Optional<UserEntity> findById(UUID id);

    List<UserEntity> findAll();

    void deleteById(UUID id);

}
