package br.com.sharetips.mappers;

import br.com.sharetips.entities.User;
import br.com.sharetips.entities.dto.user.UserAuthorDTO;
import br.com.sharetips.entities.dto.user.UserLoginRequestDTO;
import br.com.sharetips.entities.dto.user.UserRegisterRequestDTO;
import br.com.sharetips.entities.dto.user.UserUpdateRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class UserMapper {
    public static final UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    public abstract User toUser(UserLoginRequestDTO userLoginRequestDTO);
    public abstract User toUser(UserRegisterRequestDTO userRegisterRequestDTO);
    public abstract User toUser(UserAuthorDTO userAuthorDTO);
    public abstract User toUser(UserUpdateRequestDTO userUpdateRequestDTO);
}
