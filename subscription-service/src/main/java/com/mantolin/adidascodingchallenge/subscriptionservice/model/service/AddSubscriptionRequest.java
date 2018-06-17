/**
 * Copyright Mikel Antolin Sola. All rights reserved. This information is
 * confidential and proprietary to Mikel Antolin Sola and may not be used,
 * modified, copied or distributed.
 */
package com.mantolin.adidascodingchallenge.subscriptionservice.model.service;

import com.mantolin.adidascodingchallenge.subscriptionservice.model.SubscriptionRequest;

/**
 * Add subscription request representation.
 * 
 * @author Mikel Antolin Sola
 *
 */
public class AddSubscriptionRequest {

    private SubscriptionRequest subscription;

    /**
     * Constructor
     */
    public AddSubscriptionRequest() {

    }

    /**
     * Constructor
     * 
     * @param subscription
     */
    public AddSubscriptionRequest(SubscriptionRequest subscription) {
        super();
        this.subscription = subscription;
    }

    /**
     * Getter
     * 
     * @return the subscription
     */
    public SubscriptionRequest getSubscription() {
        return subscription;
    }

    /**
     * Setter
     * 
     * @param subscription
     *            the subscription to set
     */
    public void setSubscription(SubscriptionRequest subscription) {
        this.subscription = subscription;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "AddSubscriptionRequest [subscription=" + subscription + "]";
    }

}
