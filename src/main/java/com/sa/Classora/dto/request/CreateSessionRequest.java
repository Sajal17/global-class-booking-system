package com.sa.Classora.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CreateSessionRequest(

        @NotNull(message = "Offering ID is required")
        Long offeringId,

        @NotNull(message = "Start time is required")
        LocalDateTime startTime,

        @NotNull(message = "End time is required")
        LocalDateTime endTime,

        @NotBlank(message = "Timezone is required")
        String timezone
) { }
