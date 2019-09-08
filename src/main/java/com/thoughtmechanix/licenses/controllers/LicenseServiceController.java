package com.thoughtmechanix.licenses.controllers;

import com.thoughtmechanix.licenses.model.License;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/organizations/{organizationId}/licenses")
public class LicenseServiceController {

    @GetMapping(value = "/{licenseId}")
    @ResponseStatus(HttpStatus.FOUND)
    public License findById(@PathVariable("organizationId") String organizationId,
                               @PathVariable("licenseId") String licenseId) {

        return License.builder().id(licenseId)
                        .productName("Teleco")
                        .licenseType("Seat")
                        .organizationId("TestOrg")
                        .build();
    }
}
