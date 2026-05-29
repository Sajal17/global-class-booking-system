package com.sa.Classora.dto.response;

import java.util.List;

public record OfferingResponse(
        Long offeringId,

        String title,

        String courseTitle,

        String teacherName,

        String status,

        List<SessionResponse> sessions
) {
}
