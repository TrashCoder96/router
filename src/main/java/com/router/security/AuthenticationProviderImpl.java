package com.router.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus-pc on 29.09.2016.
 */

@Component
public class AuthenticationProviderImpl implements AuthenticationProvider {

    @Value("${spring.admin.username}")
    private String username;

    @Value("${spring.admin.password}")
    private String password;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        if (!authentication.getName().equals(username)) {
            throw new BadCredentialsException("User not found.");
        }

        if (!password.equals(authentication.getCredentials().toString())) {
            throw new BadCredentialsException("Wrong password or username.");
        }
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

        return new UsernamePasswordAuthenticationToken(username, authentication.getCredentials().toString(), authorities);
    }

    @Override
    public boolean supports(Class<?> authenticationClass) {
        return authenticationClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
