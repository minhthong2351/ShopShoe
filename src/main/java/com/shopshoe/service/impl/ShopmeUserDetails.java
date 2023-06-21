package com.shopshoe.service.impl;

import com.shopshoe.beans.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class ShopmeUserDetails implements UserDetails {
    private User user;

    public ShopmeUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return Collections.singleton(new SimpleGrantedAuthority(user.getRole()));
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getUserName();
    }
    public Long getId() {
        return this.user.getId();
    }
    public String getFirstName() {
        return this.user.getFirstName();
    }
    public String getLastName() {
        return this.user.getLastName();
    }
    public String getEmail() {
        return this.user.getEmail();
    }

    public String getPhone() {
        return this.user.getPhone();
    }

    public String getAddress() {
        return this.user.getAddress();
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
