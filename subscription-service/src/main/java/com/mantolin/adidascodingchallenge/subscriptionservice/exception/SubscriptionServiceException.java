/**
 * Copyright Mikel Antolin Sola. All rights reserved. This information is
 * confidential and proprietary to Mikel Antolin Sola and may not be used,
 * modified, copied or distributed.
 */
package com.mantolin.adidascodingchallenge.subscriptionservice.exception;

/**
 * Subscription Service exception representation.
 * 
 * @author Mikel Antolin Sola
 *
 */
public class SubscriptionServiceException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor
     */
    public SubscriptionServiceException() {

    }

    /**
     * Constructor
     * 
     * @param message
     */
    public SubscriptionServiceException(String message) {
        super(message);
    }
}
