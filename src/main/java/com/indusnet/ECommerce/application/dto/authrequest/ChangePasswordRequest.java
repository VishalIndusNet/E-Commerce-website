package com.indusnet.ECommerce.application.dto.authrequest;

import lombok.Data;

@Data
public class ChangePasswordRequest {

    private String email;
    private String oldPassword;
    private String newPassword;
}
