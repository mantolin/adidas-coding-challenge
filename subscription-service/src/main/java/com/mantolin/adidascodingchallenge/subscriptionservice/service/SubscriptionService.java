/**
 * Copyright Mikel Antolin Sola. All rights reserved. This information is
 * confidential and proprietary to Mikel Antolin Sola and may not be used,
 * modified, copied or distributed.
 */
package com.mantolin.adidascodingchallenge.subscriptionservice.service;

import com.mantolin.adidascodingchallenge.subscriptionservice.exception.SubscriptionServiceException;
import com.mantolin.adidascodingchallenge.subscriptionservice.model.service.AddSubscriptionRequest;
import com.mantolin.adidascodingchallenge.subscriptionservice.model.service.AddSubscriptionResponse;

/**
 * The operations allowed by the subscription service
 * 
 * @author Mikel Antolin Sola
 *
 */
public interface SubscriptionService {

    /**
     * Add a subscription
     * 
     * @param request
     * @return
     * @throws SubscriptionServiceException
     */
    public AddSubscriptionResponse addSubscription(AddSubscriptionRequest request) throws SubscriptionServiceException;

}
