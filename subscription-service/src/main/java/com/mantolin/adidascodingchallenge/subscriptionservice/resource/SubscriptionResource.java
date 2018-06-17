/**
 * Copyright Mikel Antolin Sola. All rights reserved. This information is
 * confidential and proprietary to Mikel Antolin Sola and may not be used,
 * modified, copied or distributed.
 */
package com.mantolin.adidascodingchallenge.subscriptionservice.resource;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mantolin.adidascodingchallenge.subscriptionservice.exception.ErrorDetails;
import com.mantolin.adidascodingchallenge.subscriptionservice.exception.SubscriptionServiceException;
import com.mantolin.adidascodingchallenge.subscriptionservice.model.SubscriptionRequest;
import com.mantolin.adidascodingchallenge.subscriptionservice.model.SubscriptionResponse;
import com.mantolin.adidascodingchallenge.subscriptionservice.model.service.AddSubscriptionRequest;
import com.mantolin.adidascodingchallenge.subscriptionservice.model.service.AddSubscriptionResponse;
import com.mantolin.adidascodingchallenge.subscriptionservice.service.SubscriptionService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Subscription Resource rest controller.
 * 
 * @author Mikel Antolin Sola
 *
 */
@RestController
@RequestMapping("/rest/subscription")
public class SubscriptionResource {

    private final Logger log = LoggerFactory.getLogger(SubscriptionResource.class);

    @Autowired
    SubscriptionService subscriptionService;

    /**
     * Subscription entrypoint
     * 
     * @param request
     * @return
     * @throws SubscriptionServiceException
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Creates a subscription")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Subscription successfully created.", response = SubscriptionResponse.class),
            @ApiResponse(code = 400, message = "Bad request.", response = ErrorDetails.class),
            @ApiResponse(code = 401, message = "Unauthorized. Valid access token must be provided."),
            @ApiResponse(code = 403, message = "Forbidden. The user does not have required role."),
            @ApiResponse(code = 409, message = "Conflict. There was an issue while creating the subscription.", response = ErrorDetails.class),
            @ApiResponse(code = 500, message = "Internal Server Error.", response = ErrorDetails.class)
    })
    public SubscriptionResponse subscribe(@Valid @RequestBody final SubscriptionRequest request)
            throws SubscriptionServiceException {

        log.info("Subscribe: " + request);

        AddSubscriptionRequest addRequest = new AddSubscriptionRequest(request);

        AddSubscriptionResponse addResponse = subscriptionService.addSubscription(addRequest);

        SubscriptionResponse response = new SubscriptionResponse(addResponse.getSubscription().getId());

        return response;
    }

}
