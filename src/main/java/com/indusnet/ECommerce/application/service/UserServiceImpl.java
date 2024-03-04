package com.indusnet.ECommerce.application.service;

import com.indusnet.ECommerce.application.dto.UserDTO;
import com.indusnet.ECommerce.application.response.UserResponse;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {


    @Override
    public UserResponse getAllUsers() {
        return null;
    }

    @Override
    public UserDTO getUserById(Long userId) {
        return null;
    }

    @Override
    public UserDTO updateUser(Long userId, UserDTO userDTO) {
        return null;
    }

    @Override
    public String deleteUser(Long userId) {
        return null;
    }
}
