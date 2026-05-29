package com.sa.Classora.service;

import com.sa.Classora.dto.request.BookOfferingRequest;
import com.sa.Classora.dto.response.BookingResponse;

import java.util.List;

public interface BookingService {

    BookingResponse bookOffering(
            BookOfferingRequest request
    );

    List<BookingResponse> getParentBookings(
            Long parentId
    );
}
