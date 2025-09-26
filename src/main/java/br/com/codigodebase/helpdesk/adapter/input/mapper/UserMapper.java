package br.com.codigodebase.helpdesk.adapter.input.mapper;

import br.com.codigodebase.helpdesk.adapter.input.dto.user.UserRequest;
import br.com.codigodebase.helpdesk.adapter.input.dto.user.UserResponse;
import br.com.codigodebase.helpdesk.adapter.output.entity.UserEntity;
import br.com.codigodebase.helpdesk.core.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE =  Mappers.getMapper(UserMapper.class);

    User toUser(UserRequest userRequest);

    UserResponse toResponse(User user);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    UserEntity toEntity(User user);

    User toDomain(UserEntity userEntity);
}
