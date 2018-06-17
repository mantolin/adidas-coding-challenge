/**
 * Copyright Mikel Antolin Sola. All rights reserved. This information is
 * confidential and proprietary to Mikel Antolin Sola and may not be used,
 * modified, copied or distributed.
 */
package com.mantolin.adidascodingchallenge.subscriptionservice.exception;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * Error details representation when an exception is handled.
 * 
 * @author Mikel Antolin Sola
 *
 */
public class ErrorDetails {

    @ApiModelProperty(required = true)
    private Date timestamp;

    @ApiModelProperty(required = true)
    private String message;

    @ApiModelProperty(required = true)
    private String details;

    /**
     * 
     */
    public ErrorDetails() {

    }

    /**
     * @param timestamp
     * @param message
     * @param details
     */
    public ErrorDetails(Date timestamp, String message, String details) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    /**
     * @return the timestamp
     */
    public Date getTimestamp() {
        return timestamp;
    }

    /**
     * @param timestamp
     *            the timestamp to set
     */
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message
     *            the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the details
     */
    public String getDetails() {
        return details;
    }

    /**
     * @param details
     *            the details to set
     */
    public void setDetails(String details) {
        this.details = details;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ErrorDetails [timestamp=" + timestamp + ", message=" + message + ", details=" + details + "]";
    }

}
