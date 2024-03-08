package com.indusnet.ECommerce.application.service;

import com.indusnet.ECommerce.application.dto.UserDTO;
import com.indusnet.ECommerce.application.entity.User;
import com.indusnet.ECommerce.application.exception.UserException;
import com.indusnet.ECommerce.application.repo.UserRepository;
import com.indusnet.ECommerce.application.response.UserResponse;
import com.indusnet.ECommerce.application.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    @Override
    public User findUserById(Long userId) throws Exception {

        Optional<User> user= userRepository.findById(userId);
        if(user.isPresent()){
            return user.get();
        }
        throw new UserException("user not found with id"+userId);
    }

    @Override
    public User findUserProfileByJwt(String jwt) throws Exception {
        String email= jwtProvider.getUsernameFromToken(jwt);

        Optional<User> user= userRepository.findByEmail(email);

        if(user.isPresent()){
            return user.get();
        }
        throw new UserException("user not found with email"+email);
    }

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
