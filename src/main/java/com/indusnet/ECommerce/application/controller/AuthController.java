package com.indusnet.ECommerce.application.controller;

import com.indusnet.ECommerce.application.dto.*;
import com.indusnet.ECommerce.application.entity.RefreshToken;
import com.indusnet.ECommerce.application.entity.User;
import com.indusnet.ECommerce.application.repo.UserRepository;
import com.indusnet.ECommerce.application.response.RefreshTokenResponse;
import com.indusnet.ECommerce.application.security.JwtProvider;
import com.indusnet.ECommerce.application.service.AuthService;
import com.indusnet.ECommerce.application.service.OtpService;
import com.indusnet.ECommerce.application.service.RefreshTokenServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final OtpService otpService;
    private final RefreshTokenServiceImpl refreshTokenService;

    @PostMapping("/sign-up")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm signUpForm)  {
       // return authService.registerUser(signUpForm);
        return new ResponseEntity<>(authService.registerUser(signUpForm),HttpStatus.CREATED);
    }


    @PostMapping("/sign-in")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginForm) {
      //  return authService.loginUser(loginForm);
        return new ResponseEntity<>(authService.loginUser(loginForm),HttpStatus.OK);
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshJwtToken(@Valid @RequestBody TokenRefreshRequest tokenRefreshRequest) {

        RefreshToken refreshToken = refreshTokenService.verifyRefreshToken(tokenRefreshRequest.getRefreshToken());
        User user = refreshToken.getUser();

        String accessToken = jwtProvider.generateToken(user);

        return ResponseEntity.ok(new RefreshTokenResponse(accessToken));
    }


    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOtp(@RequestBody OtpVerificationRequest request) {
        if (otpService.verifyOtp(request.getEmail(), request.getOtp())) {
            User user = userRepository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new RuntimeException("User not found with this email: " + request.getEmail()));
            user.setVerified(true);
            userRepository.save(user);
            return ResponseEntity.ok("OTP is valid. User is verified!");
        } else {
            return ResponseEntity.badRequest().body("Invalid or expired OTP. Please try again.");
        }
    }

    @PostMapping("/regenerate-otp")
    public ResponseEntity<String> regenerateOtp(@RequestBody RegenerateOtp regenerate) {
        return new ResponseEntity<>(otpService.regenerateOtp(regenerate), HttpStatus.OK);
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest){
        return  new ResponseEntity<>(authService.changePassword(changePasswordRequest),HttpStatus.OK);
    }

    @PostMapping("/forget-password")
    private ResponseEntity<?> forgetPassword(@RequestBody ForgetPasswordRequest forgetPasswordRequest){
        return  new ResponseEntity<>(authService.forgetPassword(forgetPasswordRequest),HttpStatus.OK);
    }

    @PostMapping("/verify-and-change-password")
    public ResponseEntity<?> verifyAndChangePassword(@RequestBody VerifyAndChangePassword request) {
        return authService.verifyAndChangePassword(request);
    }
}
