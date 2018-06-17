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
 * Feign client fallback for Event Service
 * 
 * @author Mikel Antolin Sola
 *
 */
@Component
public class EventServiceClientFallbackFactory implements FallbackFactory<EventServiceClient> {

    private final Logger log = LoggerFactory.getLogger(EventServiceClientFallbackFactory.class);

    /*
     * (non-Javadoc)
     * 
     * @see feign.hystrix.FallbackFactory#create(java.lang.Throwable)
     */
    @Override
    public EventServiceClient create(Throwable cause) {

        return request -> {
            log.error("Event Service fallback: " + cause);
        };
    }

}
