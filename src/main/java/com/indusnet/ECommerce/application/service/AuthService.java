package com.indusnet.ECommerce.application.service;

import com.indusnet.ECommerce.application.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public interface AuthService {

    ResponseEntity<?> registerUser(SignUpForm signUpForm) ;

    ResponseEntity<?> loginUser(LoginForm loginForm);

    ResponseEntity<?> changePassword(ChangePasswordRequest changePasswordRequest);
    ResponseEntity<?> forgetPassword(ForgetPasswordRequest forgetPasswordRequest);

    ResponseEntity<?> verifyAndChangePassword(VerifyAndChangePassword verifyAndChangePassword);

}
