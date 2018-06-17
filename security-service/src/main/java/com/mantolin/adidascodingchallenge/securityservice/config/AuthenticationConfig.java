/**
 * Copyright Mikel Antolin Sola. All rights reserved. This information is
 * confidential and proprietary to Mikel Antolin Sola and may not be used,
 * modified, copied or distributed.
 */
package com.mantolin.adidascodingchallenge.securityservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Authentication configuration. In-memory authentication manager.
 * 
 * @author Mikel Antolin Sola
 *
 */
@Configuration
public class AuthenticationConfig extends GlobalAuthenticationConfigurerAdapter {

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.security.config.annotation.authentication.configuration.
     * GlobalAuthenticationConfigurerAdapter#init(org.springframework.security.
     * config.annotation.authentication.builders.AuthenticationManagerBuilder)
     */
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        auth.inMemoryAuthentication()
                .withUser("adidasAnonym").password(passwordEncoder.encode("adidasAnonymPassword")).roles("NONE")
                .and()
                .withUser("adidasUser").password(passwordEncoder.encode("adidasUserPassword")).roles("USER")
                .and()
                .withUser("adidasAdmin").password(passwordEncoder.encode("adidasAdminPassword")).roles("USER", "ADMIN");
    }

}
