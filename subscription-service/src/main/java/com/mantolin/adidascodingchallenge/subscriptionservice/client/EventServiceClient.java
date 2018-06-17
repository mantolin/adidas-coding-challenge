/**
 * Copyright Mikel Antolin Sola. All rights reserved. This information is
 * confidential and proprietary to Mikel Antolin Sola and may not be used,
 * modified, copied or distributed.
 */
package com.mantolin.adidascodingchallenge.subscriptionservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mantolin.adidascodingchallenge.subscriptionservice.model.SubscriptionRequest;

/**
 * Feign client for Event Service
 * 
 * @author Mikel Antolin Sola
 *
 */
@FeignClient(name = "event-service", fallbackFactory = EventServiceClientFallbackFactory.class)
public interface EventServiceClient {

    @PostMapping("/rest/event")
    void event(@RequestBody final SubscriptionRequest request);

}
