package br.com.codigodebase.helpdesk.adapter.input.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE =  Mappers.getMapper(UserMapper.class);
}
