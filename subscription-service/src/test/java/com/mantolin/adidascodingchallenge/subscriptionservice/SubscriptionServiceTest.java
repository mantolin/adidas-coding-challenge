/**
 * Copyright Mikel Antolin Sola. All rights reserved. This information is
 * confidential and proprietary to Mikel Antolin Sola and may not be used,
 * modified, copied or distributed.
 */
package com.mantolin.adidascodingchallenge.subscriptionservice;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.mantolin.adidascodingchallenge.subscriptionservice.client.EmailServiceClient;
import com.mantolin.adidascodingchallenge.subscriptionservice.client.EventServiceClient;
import com.mantolin.adidascodingchallenge.subscriptionservice.exception.SubscriptionServiceException;
import com.mantolin.adidascodingchallenge.subscriptionservice.model.Subscription;
import com.mantolin.adidascodingchallenge.subscriptionservice.model.SubscriptionRequest;
import com.mantolin.adidascodingchallenge.subscriptionservice.model.service.AddSubscriptionRequest;
import com.mantolin.adidascodingchallenge.subscriptionservice.model.service.AddSubscriptionResponse;
import com.mantolin.adidascodingchallenge.subscriptionservice.repository.SubscriptionRepository;
import com.mantolin.adidascodingchallenge.subscriptionservice.service.SubscriptionService;

/**
 * Subscription Service Unit Tests.
 * 
 * @author Mikel Antolin Sola
 *
 */
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = SubscriptionServiceApplication.class)
@AutoConfigureMockMvc
public class SubscriptionServiceTest {

    @Autowired
    private SubscriptionService subscriptionService;

    @MockBean
    private SubscriptionRepository subscriptionsRepository;

    @MockBean
    private EventServiceClient eventServiceClient;

    @MockBean
    private EmailServiceClient emailServiceClient;

    @Mock
    private Subscription subscription;

    private SubscriptionRequest subscriptionRequest;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateInString = "1981-03-15";
        Date date = formatter.parse(dateInString);

        subscriptionRequest = new SubscriptionRequest("mikel.antolin@gmail.com", "Mikel", "M", date, true, 1L);
    }

    @Test
    public void testOk() throws Exception {
        when(subscriptionsRepository.findByEmail(any())).thenReturn(null);
        when(subscriptionsRepository.save(any())).thenReturn(subscription);
        when(subscription.getId()).thenReturn(1L);

        AddSubscriptionRequest request = new AddSubscriptionRequest(subscriptionRequest);
        AddSubscriptionResponse response = subscriptionService.addSubscription(request);

        assertTrue(response.getSubscription().getId() == 1L);
    }

    @Test
    public void testEmailAlreadyExists() throws Exception {
        when(subscriptionsRepository.findByEmail(any())).thenReturn(subscription);

        thrown.expect(SubscriptionServiceException.class);
        thrown.expectMessage("An account with this email address already exists");

        AddSubscriptionRequest request = new AddSubscriptionRequest(subscriptionRequest);
        subscriptionService.addSubscription(request);
    }

    @Test
    public void testSubscriptionNotCreated() throws Exception {
        when(subscriptionsRepository.findByEmail(any())).thenReturn(null);
        when(subscriptionsRepository.save(any())).thenReturn(null);

        thrown.expect(SubscriptionServiceException.class);
        thrown.expectMessage("The subscription has not been created successfully");

        AddSubscriptionRequest request = new AddSubscriptionRequest(subscriptionRequest);
        subscriptionService.addSubscription(request);
    }

    @Test
    public void testDatabaseError() throws Exception {
        when(subscriptionsRepository.findByEmail(any())).thenThrow(new RecoverableDataAccessException("MyException"));

        thrown.expect(SubscriptionServiceException.class);
        thrown.expectMessage("Database error: org.springframework.dao.RecoverableDataAccessException: MyException");

        AddSubscriptionRequest request = new AddSubscriptionRequest(subscriptionRequest);
        subscriptionService.addSubscription(request);
    }

}
