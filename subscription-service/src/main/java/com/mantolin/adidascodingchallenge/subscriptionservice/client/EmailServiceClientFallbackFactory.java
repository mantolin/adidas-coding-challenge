/**
 * Copyright Mikel Antolin Sola. All rights reserved. This information is
 * confidential and proprietary to Mikel Antolin Sola and may not be used,
 * modified, copied or distributed.
 */
package com.mantolin.adidascodingchallenge.subscriptionservice.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import feign.hystrix.FallbackFactory;

/**
 * Feign client fallback for Email Service
 * 
 * @author Mikel Antolin Sola
 *
 */
@Component
public class EmailServiceClientFallbackFactory implements FallbackFactory<EmailServiceClient> {

    private final Logger log = LoggerFactory.getLogger(EmailServiceClientFallbackFactory.class);

    /*
     * (non-Javadoc)
     * 
     * @see feign.hystrix.FallbackFactory#create(java.lang.Throwable)
     */
    @Override
    public EmailServiceClient create(Throwable cause) {

        return request -> {
            log.error("Email Service fallback: " + cause);
        };
    }

}
