/**
 * Copyright Mikel Antolin Sola. All rights reserved. This information is
 * confidential and proprietary to Mikel Antolin Sola and may not be used,
 * modified, copied or distributed.
 */
package com.mantolin.adidascodingchallenge.subscriptionservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mantolin.adidascodingchallenge.subscriptionservice.model.Subscription;

/**
 * Subscription JPA repository.
 * 
 * @author Mikel Antolin Sola
 *
 */
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    /**
     * Find a subscription by email
     * 
     * @param email
     * @return subscription
     */
    Subscription findByEmail(String email);

}
