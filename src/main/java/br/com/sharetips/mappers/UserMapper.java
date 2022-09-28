package br.com.sharetips.mappers;

import br.com.sharetips.entities.User;
import br.com.sharetips.entities.dto.user.UserLoginRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class UserMapper {
    public static final UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    public abstract User toUser(UserLoginRequestDTO userLoginRequestDTO);
}
