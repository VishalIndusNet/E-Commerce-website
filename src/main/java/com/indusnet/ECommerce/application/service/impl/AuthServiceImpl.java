package com.indusnet.ECommerce.application.service.impl;

import com.indusnet.ECommerce.application.dto.authrequest.*;
import com.indusnet.ECommerce.application.entity.RefreshToken;
import com.indusnet.ECommerce.application.entity.User;
import com.indusnet.ECommerce.application.repo.UserRepository;
import com.indusnet.ECommerce.application.response.LoginResponse;
import com.indusnet.ECommerce.application.response.RegisterResponse;
import com.indusnet.ECommerce.application.security.JwtProvider;
import com.indusnet.ECommerce.application.service.AuthService;
import com.indusnet.ECommerce.application.utils.EmailUtil;
import com.indusnet.ECommerce.application.utils.OtpUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final RefreshTokenServiceImpl refreshTokenService;
    private final OtpUtil otpUtil;
    private final EmailUtil emailUtil;
    private final OtpService otpService;


    @Override
    public ResponseEntity<?> registerUser(SignUpForm signUpForm)  {
        RegisterResponse response= new RegisterResponse();

        if (userRepository.existsByEmail(signUpForm.getEmail())) {
            return ResponseEntity.badRequest().body("Fail -> Email is already Used with Another Account     ");
        }

        // Creating user's account
        User user = new User();
        user.setFirstName(signUpForm.getFirstName());
        user.setLastName(signUpForm.getLastName());
        user.setEmail(signUpForm.getEmail());
        user.setAge(signUpForm.getAge());
        user.setGender(signUpForm.getGender());
        user.setPassword(passwordEncoder.encode(signUpForm.getPassword()));


            String generatedOtp = otpUtil.generateOtp();

            // Check if email sending is successful
            if (emailUtil.sendOtpEmail(signUpForm.getEmail(), generatedOtp)) {
                userRepository.save(user);
                response.setMessage("User Registered Successfully. Check your email for OTP verification.");
                response.setStatusCode(200);
            } else {
                response.setStatusCode(500);
                response.setMessage("Failed to send OTP email. Please try again.");
            }
            otpService.saveOtp(signUpForm.getEmail(), generatedOtp);


        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<?> loginUser(LoginForm loginForm) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginForm.getEmail(),
                        loginForm.getPassword())
        );

        User user = userRepository.findByEmail(loginForm.getEmail())
                .orElseThrow(() -> new RuntimeException("Fail! -> User not found."));

        if (!user.isVerified()) {
            return ResponseEntity.badRequest().body( "User is not verified. please verify then login");
        }

        var jwtToken = jwtProvider.generateToken(user);

        String ourEmail = jwtProvider.getUsernameFromToken(jwtToken);
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(ourEmail);
//        refreshToken = refreshTokenService.save(refreshToken);

        return ResponseEntity.ok(new LoginResponse("success", jwtToken, refreshToken.getToken()));

    }

    @Override
    public ResponseEntity<?> changePassword(ChangePasswordRequest changePasswordRequest) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authenticatedUsername = auth.getName();


        User user = userRepository.findByEmail(changePasswordRequest.getEmail())
                .orElseThrow(()-> new RuntimeException("User not exist with this email:"+changePasswordRequest.getEmail()));

        if (!authenticatedUsername.equals(user.getEmail())) {
//            throw new InvalidOperationException("You must be logged in as the correct user to change the password.");
            throw new RuntimeException("First Login then You Change Password");
        }


        if (!passwordEncoder.matches(changePasswordRequest.getOldPassword(), user.getPassword())) {
            // Handle the case where the old password is incorrect
            return ResponseEntity.badRequest().body("Incorrect old password");
        }

        // Encode and set the new password
        String newPassword = passwordEncoder.encode(changePasswordRequest.getNewPassword());

        if(passwordEncoder.matches(changePasswordRequest.getOldPassword() ,newPassword)){
            return ResponseEntity.badRequest().body("new Password and old Password cannot be same");
        }

        user.setPassword(newPassword);

        // Save the updated user
        userRepository.save(user);

        return ResponseEntity.ok("Password changed successfully");
    }

    @Override
    public ResponseEntity<?> forgetPassword(ForgetPasswordRequest forgetPasswordRequest) {
        userRepository.findByEmail(forgetPasswordRequest.getEmail())
                .orElseThrow(()-> new RuntimeException("user not found with this email:"+forgetPasswordRequest.getEmail()));

        String generatedOtp = otpUtil.generateOtp();

        if (emailUtil.sendOtpEmailForgetPassword(forgetPasswordRequest.getEmail(), generatedOtp)) {
            // Save the OTP to the database for verification
            otpService.saveOtp(forgetPasswordRequest.getEmail(), generatedOtp);
            return ResponseEntity.ok("OTP sent to your email address. Use this OTP to reset your password.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send the OTP email. Please try again.");
        }
    }

    @Override
    public ResponseEntity<?> verifyAndChangePassword(VerifyAndChangePassword verify) {

        User user = userRepository.findByEmail(verify.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found with this email: " + verify.getEmail()));


        if (otpService.verifyOtp(verify.getEmail(), verify.getOtp())) {

            if(passwordEncoder.matches(verify.getNewPassword() ,user.getPassword())){
                return ResponseEntity.badRequest().body("new Password and previous Password cannot be same");
            }

            user.setPassword(passwordEncoder.encode(verify.getNewPassword()));

            userRepository.save(user);

            return ResponseEntity.ok("Password changed successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid OTP. Please try again.");
        }
    }
}
