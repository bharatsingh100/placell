package com.project.placell.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/*
* For creating user details object
* Containing username as email, password
* and other details whether account is active or not
* required for authentication
* */

public class MyUserDetails implements UserDetails {

    // treating email from user model as username
    private String username;
    private String password;
    private boolean active;

    MyUserDetails() {}

    MyUserDetails(String username, String password, boolean active) {
        this.username= username;
        this.password = password;
        this.active = active;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.active;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.active;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.active;
    }

    @Override
    public boolean isEnabled() {
        return this.active;
    }
}
