package com.sa.Classora.controller;

import com.sa.Classora.dto.request.BookOfferingRequest;
import com.sa.Classora.dto.response.BookingResponse;
import com.sa.Classora.service.BookingService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
@Tag(name = "Booking APIs")
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public BookingResponse bookOffering(
            @Valid @RequestBody
            BookOfferingRequest request
    ) {

        return bookingService.bookOffering(request);
    }

    @GetMapping("/parent/{parentId}")
    public List<BookingResponse> getParentBookings(
            @PathVariable Long parentId
    ) {

        return bookingService.getParentBookings(
                parentId
        );
    }
}
