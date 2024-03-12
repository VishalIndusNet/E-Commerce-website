package com.indusnet.ECommerce.application.dto.authrequest;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class  SignUpForm {

    @NotBlank(message = "firstName can not be blank")
    @Size(min = 3, max = 20, message = "First Name must be between 3 and 20 characters long")
    @Pattern(regexp = "^[[A-Z]{1}[a-z]{2,}]*$", message = "First Name must not contain numbers or special characters")
    private String firstName;

    @NotBlank(message = "Last name cannot be blank")
    @Size(min = 3, max = 20, message = "Last Name must be between 3 and 20 characters long")
    @Pattern(regexp = "^[A-Z{1}a-z{2,}]*$", message = "Last Name must not contain numbers or special characters")
    private String lastName;

    @NotBlank
    @Size(max = 60)
    @Email(message = "Please enter email in proper format!")
    private String email;


    @Column(name = "user_age")
    @NotNull(message = "Age must not be null")
    @Min(value = 18, message = "Age must be greater than or equal to 18")
    private Integer age;

    // @Pattern(regexp = "[mf]", message = "gender must be 'm' or 'f'")
    private String gender;

    private Set<String> role;


    @Size(min = 10, max = 10, message = "Mobile Number must be exactly 10 digits long")
    @Pattern(regexp = "^[6-9]{1}[0-9]{9}$", message = "Mobile Number must contain only Numbers")
    private String mobileNumber;

    @NotBlank(message = "Password cannot be null")
    @Size(min = 6, max = 40)
    private String password;
}
