/**
 * Copyright Mikel Antolin Sola. All rights reserved. This information is
 * confidential and proprietary to Mikel Antolin Sola and may not be used,
 * modified, copied or distributed.
 */
package com.mantolin.adidascodingchallenge.securityservice.resource;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Security Resource rest controller.
 * 
 * @author Mikel Antolin Sola
 *
 */
@RestController
@RequestMapping("/rest/security")
public class SecurityResource {

    @GetMapping("/user-info")
    @ApiOperation(value = "Provides user information")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success."),
            @ApiResponse(code = 400, message = "Bad request."),
            @ApiResponse(code = 401, message = "Unauthorized."),
            @ApiResponse(code = 500, message = "Internal Server Error.")
    })
    public Principal user(Principal user) {
        return user;
    }

}
