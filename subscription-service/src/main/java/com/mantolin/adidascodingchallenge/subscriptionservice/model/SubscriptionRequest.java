/**
 * Copyright Mikel Antolin Sola. All rights reserved. This information is
 * confidential and proprietary to Mikel Antolin Sola and may not be used,
 * modified, copied or distributed.
 */
package com.mantolin.adidascodingchallenge.subscriptionservice.model;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;

/**
 * Subscription request representation.
 * 
 * @author Mikel Antolin Sola
 *
 */
public class SubscriptionRequest {

    @NotEmpty(message = "'email' cannot be null or empty")
    @Email(message = "'email' must be a valid email address")
    @Size(max = 100, message = "'email' must be a max of 100 characters")
    @ApiModelProperty(required = true)
    private String email;

    @Size(max = 20, message = "'firstName' must be a max of 20 characters")
    private String firstName;

    @Pattern(regexp = "[MFO]", message = "'gender' must be one of the following values: M (Male), F (Female), O (Other)")
    @ApiModelProperty(allowableValues = "M,F,O")
    private String gender;

    @NotNull(message = "'dateOfBirth' cannot be null")
    @Past(message = "'dateOfBirth' must be a date in the past")
    @ApiModelProperty(required = true)
    private Date dateOfBirth;

    @NotNull(message = "'flag for consent' cannot be null")
    @ApiModelProperty(required = true)
    private Boolean consent;

    @NotNull(message = "'newsletter Id' cannot be null")
    @Positive(message = "'newsletter Id' must be a positive number")
    @ApiModelProperty(required = true)
    private Long newsletterId;

    /**
     * Constructor
     */
    public SubscriptionRequest() {

    }

    /**
     * Constructor
     * 
     * @param email
     * @param firstName
     * @param gender
     * @param dateOfBirth
     * @param consent
     * @param newsletterId
     */
    public SubscriptionRequest(String email, String firstName, String gender, Date dateOfBirth, Boolean consent,
            Long newsletterId) {
        this.email = email;
        this.firstName = firstName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.consent = consent;
        this.newsletterId = newsletterId;
    }

    /**
     * Getter
     * 
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter
     * 
     * @param email
     *            the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter
     * 
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Setter
     * 
     * @param firstName
     *            the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Getter
     * 
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * Setter
     * 
     * @param gender
     *            the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Getter
     * 
     * @return the dateOfBirth
     */
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Setter
     * 
     * @param dateOfBirth
     *            the dateOfBirth to set
     */
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Getter
     * 
     * @return the consent
     */
    public Boolean isConsent() {
        return consent;
    }

    /**
     * Setter
     * 
     * @param consent
     *            the consent to set
     */
    public void setConsent(Boolean consent) {
        this.consent = consent;
    }

    /**
     * Getter
     * 
     * @return the newsletterId
     */
    public Long getNewsletterId() {
        return newsletterId;
    }

    /**
     * Setter
     * 
     * @param newsletterId
     *            the newsletterId to set
     */
    public void setNewsletterId(Long newsletterId) {
        this.newsletterId = newsletterId;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "SubscriptionRequest [email=" + email + ", firstName=" + firstName + ", gender=" + gender
                + ", dateOfBirth=" + dateOfBirth + ", consent=" + consent + ", newsletterId=" + newsletterId + "]";
    }

}
