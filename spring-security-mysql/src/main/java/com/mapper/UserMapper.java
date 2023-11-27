package com.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import com.dao.User;
import com.dto.UserDTO;

@Mapper
public interface UserMapper {

  UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

  @Mappings({@Mapping(source = "role.rolename", target = "role")})
  UserDTO userToUserDTO(User user);

  List<UserDTO> usersToUserDTOs(List<User> users);

}
