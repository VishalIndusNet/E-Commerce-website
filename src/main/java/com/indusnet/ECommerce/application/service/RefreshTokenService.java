package com.indusnet.ECommerce.application.service;

import com.indusnet.ECommerce.application.entity.RefreshToken;
import org.springframework.stereotype.Component;

@Component
public interface RefreshTokenService {

    RefreshToken createRefreshToken(String email) ;
    RefreshToken verifyRefreshToken(String refreshToken);
}
