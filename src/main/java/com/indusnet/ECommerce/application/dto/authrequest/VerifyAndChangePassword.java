package com.indusnet.ECommerce.application.dto.authrequest;

import lombok.Data;

@Data
public class VerifyAndChangePassword {
    private  String email;
    private String otp;
    private String newPassword;
}
