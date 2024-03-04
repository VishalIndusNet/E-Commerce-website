package com.indusnet.ECommerce.application.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Size(min = 3, max = 20, message = "First Name must be between 3 and 20 characters long")
    @Pattern(regexp = "^[[A-Z]{1}[a-z]{2,}]*$", message = "First Name must not contain numbers or special characters")
    private String firstName;

    @Size(min = 3, max = 20, message = "Last Name must be between 3 and 20 characters long")
    @Pattern(regexp = "^[A-Z{1}a-z{2,}]*$", message = "Last Name must not contain numbers or special characters")
    private String lastName;

    @Size(min = 10, max = 10, message = "Mobile Number must be exactly 10 digits long")
    @Pattern(regexp = "^[6-9]{1}[0-9]{9}$", message = "Mobile Number must contain only Numbers")
    private String mobileNumber;

    @Column(name = "user_age")
    private Integer age;

    private String gender;

    @Email
    @Column(unique = true, nullable = false)
    private String email;

    @Column(name = "user_password")
    @Size(min = 6)
    private String password;

    private boolean isVerified = false;


    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @OneToOne(mappedBy = "user")
    private RefreshToken refreshToken;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private OtpEntity otp;

    @OneToOne(mappedBy = "user", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, orphanRemoval = true)
    private Cart cart;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
