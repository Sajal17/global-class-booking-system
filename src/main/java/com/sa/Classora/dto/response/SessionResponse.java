package com.sa.Classora.dto.response;

public record SessionResponse(

        Long sessionId,

        String startTime,

        String endTime
) {
}
