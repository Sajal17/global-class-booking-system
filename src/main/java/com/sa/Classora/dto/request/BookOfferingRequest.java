package com.sa.Classora.dto.request;

import jakarta.validation.constraints.NotNull;

public record BookOfferingRequest(

        @NotNull(message = "Parent ID is required")
        Long parentId,

        @NotNull(message = "Offering ID is required")
        Long offeringId
) { }
