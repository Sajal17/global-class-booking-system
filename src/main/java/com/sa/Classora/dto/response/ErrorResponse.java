package com.sa.Classora.dto.response;

import java.time.Instant;

public record ErrorResponse(
        int status,

        String message,

        Instant timestamp
) { }
