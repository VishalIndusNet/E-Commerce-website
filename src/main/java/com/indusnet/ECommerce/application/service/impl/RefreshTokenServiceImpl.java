package com.indusnet.ECommerce.application.service.impl;

import com.indusnet.ECommerce.application.entity.RefreshToken;
import com.indusnet.ECommerce.application.entity.User;
import com.indusnet.ECommerce.application.repo.RefreshTokenRepository;
import com.indusnet.ECommerce.application.repo.UserRepository;
import com.indusnet.ECommerce.application.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;


    public RefreshToken createRefreshToken(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email : " + email));

        RefreshToken refreshToken = user.getRefreshToken();

        if (refreshToken == null) {
            long refreshTokenValidity = 3 * 60 * 60 * 1000;
            refreshToken = new RefreshToken();
            refreshToken.setExpirationTime(Instant.now().plusMillis(refreshTokenValidity));
            refreshToken.setToken(UUID.randomUUID().toString());
            refreshToken.setUser(user); // Associate the user with the refresh token

            refreshTokenRepository.save(refreshToken);
        }

        return refreshToken;
    }

    public RefreshToken verifyRefreshToken(String refreshToken) {
        RefreshToken refToken = refreshTokenRepository.findByToken(refreshToken)
                .orElseThrow(() -> new RuntimeException("Refresh token not found!"));

        if (refToken.getExpirationTime().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(refToken);
            throw new RuntimeException("Refresh Token expired");
        }
        return refToken;
    }
}
