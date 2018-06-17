/**
 * Copyright Mikel Antolin Sola. All rights reserved. This information is
 * confidential and proprietary to Mikel Antolin Sola and may not be used,
 * modified, copied or distributed.
 */
package com.mantolin.adidascodingchallenge.subscriptionservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * Active for dev and production profile.
 * 
 * Enables Resource Server. It configures authentication whitelist url and the
 * urls that require authentication and what role is required for authorization
 * 
 * @author Mikel Antolin Sola
 *
 */
@Profile(value = { "dev", "production" })
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private static final String[] AUTH_WHITELIST = {
            // -- swagger ui
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            // -- actuator
            "/actuator/**"
    };

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.security.oauth2.config.annotation.web.configuration.
     * ResourceServerConfigurerAdapter#configure(org.springframework.security.config
     * .annotation.web.builders.HttpSecurity)
     */
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(AUTH_WHITELIST).permitAll()
                .anyRequest().hasRole("USER");
    }

}
