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
 * Feign client for Email Service
 * 
 * @author Mikel Antolin Sola
 *
 */
@FeignClient(name = "email-service", fallbackFactory = EmailServiceClientFallbackFactory.class)
public interface EmailServiceClient {

    @PostMapping("/rest/email")
    void email(@RequestBody final SubscriptionRequest request);
}
