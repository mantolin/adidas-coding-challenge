/**
 * Copyright Mikel Antolin Sola. All rights reserved. This information is
 * confidential and proprietary to Mikel Antolin Sola and may not be used,
 * modified, copied or distributed.
 */
package com.mantolin.adidascodingchallenge.emailservice.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mantolin.adidascodingchallenge.emailservice.model.SubscriptionRequest;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Email Resource rest controller
 * 
 * @author Mikel Antolin Sola
 *
 */
@RestController
@RequestMapping("/rest/email")
public class EmailResource {

    private final Logger log = LoggerFactory.getLogger(EmailResource.class);

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Email service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success."),
            @ApiResponse(code = 400, message = "Bad request."),
            @ApiResponse(code = 500, message = "Internal Server Error.")
    })
    public void email(@RequestBody final SubscriptionRequest request) {
        log.info("Email: " + request);
    }

}
