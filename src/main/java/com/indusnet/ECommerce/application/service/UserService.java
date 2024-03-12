package com.indusnet.ECommerce.application.service;

import com.indusnet.ECommerce.application.dto.UserDTO;
import com.indusnet.ECommerce.application.entity.User;
import com.indusnet.ECommerce.application.exception.UserException;
import com.indusnet.ECommerce.application.response.UserResponse;

public interface UserService {

    User findUserById(Long userId) throws Exception;

    User findUserProfileByJwt(String jwt) throws Exception;



//    UserResponse getAllUsers();
//
//    UserDTO getUserById(Long userId);
//
//    UserDTO updateUser(Long userId, UserDTO userDTO);
//
//    String deleteUser(Long userId);
}
