package com.knowlode.service;

import com.knowlode.dto.UserDTO;
import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);
    UserDTO updateUser(Long id, UserDTO userDTO);
    void deleteUser(Long id);
    UserDTO getUserById(Long id);
    List<UserDTO> getAllUsers();
    UserDTO getUserByUsername(String username);
} 