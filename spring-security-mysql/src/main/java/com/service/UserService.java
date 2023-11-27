package com.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.User;
import com.dao.UserRepository;
import com.dto.UserDTO;
import com.mapper.UserMapper;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public List<UserDTO> getPublicDetails() {
    return null;
  }

  public UserDTO getBasicDetails(String username) {
    User user = userRepository.findByEmail(username);
    UserDTO userDTO = UserMapper.INSTANCE.userToUserDTO(user);
    return userDTO;
  }

  public List<UserDTO> getFullDetails(String username) {
    User user = userRepository.findByEmail(username);
    String roleName = user.getRole().getRolename();
    List<UserDTO> userDTOs = new ArrayList<UserDTO>();
    if (roleName.equals("ADMIN")) {
      List<User> users = userRepository.findAll();
      userDTOs = UserMapper.INSTANCE.usersToUserDTOs(users);
    }
    return userDTOs;
  }

}
