package br.com.codigodebase.helpdesk.adapter.input.mapper;

import br.com.codigodebase.helpdesk.adapter.input.request.UserRequest;
import br.com.codigodebase.helpdesk.adapter.input.response.UserResponse;
import br.com.codigodebase.helpdesk.core.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE =  Mappers.getMapper(UserMapper.class);

    User toUser(UserRequest userRequest);

    UserResponse toUserResponse(User user);
}
