/**
 * Copyright Mikel Antolin Sola. All rights reserved. This information is
 * confidential and proprietary to Mikel Antolin Sola and may not be used,
 * modified, copied or distributed.
 */
package com.mantolin.adidascodingchallenge.subscriptionservice.model;

import io.swagger.annotations.ApiModelProperty;

/**
 * Subscription response representation.
 * 
 * @author Mikel Antolin Sola
 *
 */
public class SubscriptionResponse {

    @ApiModelProperty(required = true)
    private long subscriptionId;

    /**
     * 
     */
    public SubscriptionResponse() {

    }

    /**
     * @param subscriptionId
     */
    public SubscriptionResponse(long subscriptionId) {
        super();
        this.subscriptionId = subscriptionId;
    }

    /**
     * @return the subscriptionId
     */
    public long getSubscriptionId() {
        return subscriptionId;
    }

    /**
     * @param subscriptionId
     *            the subscriptionId to set
     */
    public void setSubscriptionId(long subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "SubscriptionResponse [subscriptionId=" + subscriptionId + "]";
    }

}
