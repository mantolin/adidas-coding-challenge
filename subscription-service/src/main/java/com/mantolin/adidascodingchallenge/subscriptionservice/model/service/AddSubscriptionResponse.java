/**
 * 
 * Copyright Mikel Antolin Sola. All rights reserved. This information is
 * confidential and proprietary to Mikel Antolin Sola and may not be used,
 * modified, copied or distributed.
 */
package com.mantolin.adidascodingchallenge.subscriptionservice.model.service;

import com.mantolin.adidascodingchallenge.subscriptionservice.model.Subscription;

/**
 * Add subscription response representation.
 * 
 * @author Mikel Antolin Sola
 *
 */
public class AddSubscriptionResponse {

    private Subscription subscription;

    /**
     * Constructor
     */
    public AddSubscriptionResponse() {

    }

    /**
     * Constructor
     * 
     * @param subscription
     */
    public AddSubscriptionResponse(Subscription subscription) {
        super();
        this.subscription = subscription;
    }

    /**
     * Getter
     * 
     * @return the subscription
     */
    public Subscription getSubscription() {
        return subscription;
    }

    /**
     * Setter
     * 
     * @param subscription
     *            the subscription to set
     */
    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "AddSubscriptionResponse [subscription=" + subscription + "]";
    }

}
