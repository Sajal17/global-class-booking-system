package com.sa.Classora.dto.response;

public record BookingResponse(
        Long bookingId,

        Long offeringId,

        String offeringTitle,

        String bookingStatus,

        String bookedAt
) {
}
