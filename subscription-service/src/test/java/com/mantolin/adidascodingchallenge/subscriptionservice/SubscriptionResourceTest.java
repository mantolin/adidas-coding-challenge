/**
 * Copyright Mikel Antolin Sola. All rights reserved. This information is
 * confidential and proprietary to Mikel Antolin Sola and may not be used,
 * modified, copied or distributed.
 */
package com.mantolin.adidascodingchallenge.subscriptionservice;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.mantolin.adidascodingchallenge.subscriptionservice.model.Subscription;
import com.mantolin.adidascodingchallenge.subscriptionservice.model.service.AddSubscriptionResponse;
import com.mantolin.adidascodingchallenge.subscriptionservice.resource.SubscriptionResource;
import com.mantolin.adidascodingchallenge.subscriptionservice.service.SubscriptionService;

/**
 * Subscription Resource Unit Tests
 * 
 * @author Mikel Antolin Sola
 *
 */
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = SubscriptionServiceApplication.class)
@AutoConfigureMockMvc
public class SubscriptionResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    SubscriptionResource resource;

    @MockBean
    private SubscriptionService subscriptionService;

    @Mock
    private Subscription subscription;

    @Mock
    private AddSubscriptionResponse addSubscriptionResponse;

    @Before
    public void setUp() throws Exception {
        when(subscriptionService.addSubscription(any())).thenReturn(addSubscriptionResponse);
        when(addSubscriptionResponse.getSubscription()).thenReturn(subscription);
        when(subscription.getId()).thenReturn(1L);
    }

    @Test
    public void testSubscriptionOk() throws Exception {

        String json = "{\n" +
                "  \"email\": \"mikel.leitza@gmail.com\",\n" +
                "  \"firstName\": \"Mikel\",\n" +
                "  \"gender\": \"M\",\n" +
                "  \"dateOfBirth\": \"1981-03-15\",\n" +
                "  \"consent\": true,\n" +
                "  \"newsletterId\": 1\n" +
                "}";

        mockMvc.perform(
                MockMvcRequestBuilders.post("/rest/subscription")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testSubscriptionMissingEmail() throws Exception {

        String json = "{\n" +
                "  \"firstName\": \"Mikel\",\n" +
                "  \"gender\": \"M\",\n" +
                "  \"dateOfBirth\": \"1981-03-15\",\n" +
                "  \"consent\": true,\n" +
                "  \"newsletterId\": 1\n" +
                "}";

        mockMvc.perform(
                MockMvcRequestBuilders.post("/rest/subscription")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void testSubscriptionInvalidEmail() throws Exception {

        String json = "{\n" +
                "  \"email\": \"mikel.leitza#gmail.com\",\n" +
                "  \"firstName\": \"Mikel\",\n" +
                "  \"gender\": \"M\",\n" +
                "  \"dateOfBirth\": \"1981-03-15\",\n" +
                "  \"consent\": true,\n" +
                "  \"newsletterId\": 1\n" +
                "}";

        mockMvc.perform(
                MockMvcRequestBuilders.post("/rest/subscription")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void testSubscriptionInvalidEmailSize() throws Exception {

        String json = "{\n" +
                "  \"email\": \"mikel.leitzaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa@gmail.com\",\n"
                +
                "  \"firstName\": \"Mikel\",\n" +
                "  \"gender\": \"M\",\n" +
                "  \"dateOfBirth\": \"1981-03-15\",\n" +
                "  \"consent\": true,\n" +
                "  \"newsletterId\": 1\n" +
                "}";

        mockMvc.perform(
                MockMvcRequestBuilders.post("/rest/subscription")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void testSubscriptionInvalidFirstNameSize() throws Exception {

        String json = "{\n" +
                "  \"email\": \"mikel.leitza@gmail.com\",\n" +
                "  \"firstName\": \"MikelMikelMikelMikelMikel\",\n" +
                "  \"gender\": \"M\",\n" +
                "  \"dateOfBirth\": \"1981-03-15\",\n" +
                "  \"consent\": true,\n" +
                "  \"newsletterId\": 1\n" +
                "}";

        mockMvc.perform(
                MockMvcRequestBuilders.post("/rest/subscription")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void testSubscriptionInvalidGender() throws Exception {

        String json = "{\n" +
                "  \"email\": \"mikel.leitza@gmail.com\",\n" +
                "  \"firstName\": \"Mikel\",\n" +
                "  \"gender\": \"X\",\n" +
                "  \"dateOfBirth\": \"1981-03-15\",\n" +
                "  \"consent\": true,\n" +
                "  \"newsletterId\": 1\n" +
                "}";

        mockMvc.perform(
                MockMvcRequestBuilders.post("/rest/subscription")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void testSubscriptionMissingDateOfBirth() throws Exception {

        String json = "{\n" +
                "  \"email\": \"mikel.leitza@gmail.com\",\n" +
                "  \"firstName\": \"Mikel\",\n" +
                "  \"gender\": \"M\",\n" +
                "  \"consent\": true,\n" +
                "  \"newsletterId\": 1\n" +
                "}";

        mockMvc.perform(
                MockMvcRequestBuilders.post("/rest/subscription")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void testSubscriptionInvalidDateOfBirth() throws Exception {

        String json = "{\n" +
                "  \"email\": \"mikel.leitza@gmail.com\",\n" +
                "  \"firstName\": \"Mikel\",\n" +
                "  \"gender\": \"M\",\n" +
                "  \"dateOfBirth\": \"2100-03-15\",\n" +
                "  \"consent\": true,\n" +
                "  \"newsletterId\": 1\n" +
                "}";

        mockMvc.perform(
                MockMvcRequestBuilders.post("/rest/subscription")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void testSubscriptionMissingConsent() throws Exception {

        String json = "{\n" +
                "  \"email\": \"mikel.leitza@gmail.com\",\n" +
                "  \"firstName\": \"Mikel\",\n" +
                "  \"gender\": \"M\",\n" +
                "  \"dateOfBirth\": \"1981-03-15\",\n" +
                "  \"newsletterId\": 1\n" +
                "}";

        mockMvc.perform(
                MockMvcRequestBuilders.post("/rest/subscription")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void testSubscriptionMissingNewsletterId() throws Exception {

        String json = "{\n" +
                "  \"email\": \"mikel.leitza@gmail.com\",\n" +
                "  \"firstName\": \"Mikel\",\n" +
                "  \"gender\": \"M\",\n" +
                "  \"dateOfBirth\": \"1981-03-15\",\n" +
                "  \"consent\": true\n" +
                "}";

        mockMvc.perform(
                MockMvcRequestBuilders.post("/rest/subscription")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void testSubscriptionInvalidNewsletterId() throws Exception {

        String json = "{\n" +
                "  \"email\": \"mikel.leitza@gmail.com\",\n" +
                "  \"firstName\": \"Mikel\",\n" +
                "  \"gender\": \"M\",\n" +
                "  \"dateOfBirth\": \"1981-03-15\",\n" +
                "  \"consent\": true,\n" +
                "  \"newsletterId\": -1\n" +
                "}";

        mockMvc.perform(
                MockMvcRequestBuilders.post("/rest/subscription")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

}
