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
 * Active for test profile.
 * 
 * Enables Resource Server. It disables authentication for all requests.
 * 
 * @author Mikel Antolin Sola
 *
 */
@Profile(value = { "test" })
@Configuration
@EnableResourceServer
public class ResourceServerTestConfig extends ResourceServerConfigurerAdapter {

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
                .anyRequest().permitAll();
    }
}
