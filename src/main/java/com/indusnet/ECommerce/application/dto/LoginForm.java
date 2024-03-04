package com.indusnet.ECommerce.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginForm {

    @NotBlank
    @Size(max = 60)
    @Email(message = "Please enter email in proper format!")
    private String email;

    @NotBlank(message = "Password cannot be null")
    @Size(min = 6, max = 40)
    private String password;
}
