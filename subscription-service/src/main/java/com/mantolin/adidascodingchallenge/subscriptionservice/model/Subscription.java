/**
 * Copyright Mikel Antolin Sola. All rights reserved. This information is
 * confidential and proprietary to Mikel Antolin Sola and may not be used,
 * modified, copied or distributed.
 */
package com.mantolin.adidascodingchallenge.subscriptionservice.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Subscription entity representation.
 * 
 * @author Mikel Antolin Sola
 *
 */
@Entity
@Table(name = "subscriptions")
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "gender")
    private String gender;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "consent")
    private Boolean consent;

    @Column(name = "newsletter_id")
    private Long newsletterId;

    /**
     * Constructor
     */
    protected Subscription() {

    }

    /**
     * @param email
     * @param firstName
     * @param gender
     * @param dateOfBirth
     * @param consent
     * @param newsletterId
     */
    private Subscription(String email, String firstName, String gender, Date dateOfBirth, Boolean consent,
            Long newsletterId) {
        this.email = email;
        this.firstName = firstName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.consent = consent;
        this.newsletterId = newsletterId;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @return the dateOfBirth
     */
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * @return the consent
     */
    public Boolean getConsent() {
        return consent;
    }

    /**
     * @return the newsletterId
     */
    public Long getNewsletterId() {
        return newsletterId;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Subscription [id=" + id + ", email=" + email + ", firstName=" + firstName + ", gender=" + gender
                + ", dateOfBirth=" + dateOfBirth + ", consent=" + consent + ", newsletterId=" + newsletterId + "]";
    }

    /**
     * Builder
     * 
     * @return SubscriptionBuilder
     */
    public static SubscriptionBuilder builder() {
        return new SubscriptionBuilder();
    }

    /**
     * @author GZLQ3X
     *
     */
    public static class SubscriptionBuilder {

        private String email;
        private String firstName;
        private String gender;
        private Date dateOfBirth;
        private Boolean consent;
        private Long newsletterId;

        /**
         * @param email
         *            the email to set
         * @return SubscriptionBuilder
         */
        public SubscriptionBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        /**
         * @param firstName
         *            the firstName to set
         * @return SubscriptionBuilder
         */
        public SubscriptionBuilder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        /**
         * @param gender
         *            the gender to set
         * @return SubscriptionBuilder
         */
        public SubscriptionBuilder setGender(String gender) {
            this.gender = gender;
            return this;
        }

        /**
         * @param dateOfBirth
         *            the dateOfBirth to set
         * @return SubscriptionBuilder
         */
        public SubscriptionBuilder setDateOfBirth(Date dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        /**
         * @param consent
         *            the consent to set
         * @return SubscriptionBuilder
         */
        public SubscriptionBuilder setConsent(Boolean consent) {
            this.consent = consent;
            return this;
        }

        /**
         * @param newsletterId
         *            the newsletterId to set
         * @return SubscriptionBuilder
         */
        public SubscriptionBuilder setNewsletterId(Long newsletterId) {
            this.newsletterId = newsletterId;
            return this;
        }

        /**
         * Builder
         * 
         * @return Subscription
         */
        public Subscription build() {
            return new Subscription(email, firstName, gender, dateOfBirth, consent, newsletterId);
        }
    }

}
