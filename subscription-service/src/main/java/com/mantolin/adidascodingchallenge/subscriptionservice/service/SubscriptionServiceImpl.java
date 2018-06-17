/**
 * Copyright Mikel Antolin Sola. All rights reserved. This information is
 * confidential and proprietary to Mikel Antolin Sola and may not be used,
 * modified, copied or distributed.
 */
package com.mantolin.adidascodingchallenge.subscriptionservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.mantolin.adidascodingchallenge.subscriptionservice.client.EmailServiceClient;
import com.mantolin.adidascodingchallenge.subscriptionservice.client.EventServiceClient;
import com.mantolin.adidascodingchallenge.subscriptionservice.exception.SubscriptionServiceException;
import com.mantolin.adidascodingchallenge.subscriptionservice.model.Subscription;
import com.mantolin.adidascodingchallenge.subscriptionservice.model.SubscriptionRequest;
import com.mantolin.adidascodingchallenge.subscriptionservice.model.service.AddSubscriptionRequest;
import com.mantolin.adidascodingchallenge.subscriptionservice.model.service.AddSubscriptionResponse;
import com.mantolin.adidascodingchallenge.subscriptionservice.repository.SubscriptionRepository;

/**
 * Subscription service implementation.
 * 
 * @author Mikel Antolin Sola
 *
 */
@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    private final Logger log = LoggerFactory.getLogger(SubscriptionServiceImpl.class);

    @Autowired
    private SubscriptionRepository subscriptionsRepository;

    @Autowired
    EventServiceClient eventServiceClient;

    @Autowired
    EmailServiceClient emailServiceClient;

    /*
     * (non-Javadoc)
     * 
     * @see com.mantolin.adidascodingchallenge.subscriptionservice.service.
     * SubscriptionService#addSubscription(com.mantolin.adidascodingchallenge.
     * subscriptionservice.model.service.AddSubscriptionRequest)
     */
    @Override
    public AddSubscriptionResponse addSubscription(AddSubscriptionRequest addRequest)
            throws SubscriptionServiceException {

        Subscription result;
        String errMsg;

        try {
            SubscriptionRequest request = addRequest.getSubscription();

            // Check if a subscription with same email already exists
            if (subscriptionsRepository.findByEmail(request.getEmail()) != null) {
                errMsg = "An account with this email address already exists";
                log.error(errMsg);
                throw new SubscriptionServiceException(errMsg);
            }

            Subscription subscription = Subscription.builder()
                    .setEmail(request.getEmail())
                    .setFirstName(request.getFirstName())
                    .setGender(request.getGender())
                    .setDateOfBirth(request.getDateOfBirth())
                    .setConsent(request.isConsent())
                    .setNewsletterId(request.getNewsletterId())
                    .build();

            // Create the subscription
            result = subscriptionsRepository.save(subscription);

            if (result == null) {
                errMsg = "The subscription has not been created successfully";
                log.error(errMsg);
                throw new SubscriptionServiceException(errMsg);
            }

            log.info("Subscrition added with id: " + result.getId());

            // Call Event Service
            eventServiceClient.event(request);

            // Call Email Service
            emailServiceClient.email(request);

        } catch (DataAccessException e) {
            errMsg = String.format("Database error: %s", e);
            log.error(errMsg);
            throw new SubscriptionServiceException(errMsg);
        }

        AddSubscriptionResponse addResponse = new AddSubscriptionResponse(result);

        return addResponse;
    }

}
