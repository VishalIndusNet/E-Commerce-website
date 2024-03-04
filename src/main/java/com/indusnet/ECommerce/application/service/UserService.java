package com.indusnet.ECommerce.application.service;

import com.indusnet.ECommerce.application.dto.UserDTO;
import com.indusnet.ECommerce.application.response.UserResponse;

public interface UserService {

    UserResponse getAllUsers();

    UserDTO getUserById(Long userId);

    UserDTO updateUser(Long userId, UserDTO userDTO);

    String deleteUser(Long userId);
}
