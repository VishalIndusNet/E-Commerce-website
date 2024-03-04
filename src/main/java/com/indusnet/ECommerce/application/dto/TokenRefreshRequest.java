package com.indusnet.ECommerce.application.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenRefreshRequest {

    @NotBlank(message = "Refresh token cannot be blank")
    private String refreshToken;
}
