package com.example.demo.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.model.User;

import java.time.LocalDate;
import java.util.Collection;

public class CustomUserDetails implements UserDetails {

    private final User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    public Long getId() {
        return user.getId();
    }

    public User getUser() {
        return user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .toList();
    }

    @Override
    public String getPassword() { 
        return user.getPassword(); 
    }

    @Override
    public String getUsername() { 
        return user.getUsername(); 
    }

    @Override
    public boolean isAccountNonExpired() {
        return user.getAccountExpirationDate() == null || 
            user.getAccountExpirationDate().isAfter(LocalDate.now());
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.getAccountLockedDate() == null || 
            user.getAccountLockedDate().isAfter(LocalDate.now());
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return user.getCredentialsExpirationDate() == null || 
            user.getCredentialsExpirationDate().isAfter(LocalDate.now());
    }

    @Override
    public boolean isEnabled() {
         return user.getAccountEnabledDate() == null || 
            user.getAccountEnabledDate().isAfter(LocalDate.now());
    }
}
