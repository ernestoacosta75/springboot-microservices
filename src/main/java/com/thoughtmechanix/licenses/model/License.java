package com.thoughtmechanix.licenses.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class License {

    private String id;
    private String organizationId;
    private String productName;
    private String licenseType;

    public License(String id, String organizationId, String productName, String licenseType) {
        this.id = id;
        this.organizationId = organizationId;
        this.productName = productName;
        this.licenseType = licenseType;
    }
}
