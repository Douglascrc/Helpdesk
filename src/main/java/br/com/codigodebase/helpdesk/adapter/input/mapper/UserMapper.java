package br.com.codigodebase.helpdesk.adapter.input.mapper;

import br.com.codigodebase.helpdesk.adapter.input.dto.user.UserRequest;
import br.com.codigodebase.helpdesk.adapter.input.dto.user.UserResponse;
import br.com.codigodebase.helpdesk.adapter.output.entity.UserEntity;
import br.com.codigodebase.helpdesk.core.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE =  Mappers.getMapper(UserMapper.class);

    User toUser(UserRequest userRequest);

    UserResponse toResponse(User user);

    UserEntity toEntity(User user);

    User toDomain(UserEntity userEntity);
}
