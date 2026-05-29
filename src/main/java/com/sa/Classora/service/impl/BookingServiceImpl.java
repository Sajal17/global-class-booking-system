package com.sa.Classora.service.impl;

import com.sa.Classora.dto.request.BookOfferingRequest;
import com.sa.Classora.dto.response.BookingResponse;
import com.sa.Classora.emums.BookingStatus;
import com.sa.Classora.entity.Booking;
import com.sa.Classora.entity.Offering;
import com.sa.Classora.entity.Parent;
import com.sa.Classora.entity.Session;
import com.sa.Classora.exception.ConflictException;
import com.sa.Classora.exception.DuplicateBookingException;
import com.sa.Classora.exception.ResourceNotFoundException;
import com.sa.Classora.repository.BookingRepository;
import com.sa.Classora.repository.OfferingRepository;
import com.sa.Classora.repository.ParentRepository;
import com.sa.Classora.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final ParentRepository parentRepository;
    private final OfferingRepository offeringRepository;


    @Override
    public BookingResponse bookOffering(

            BookOfferingRequest request
    ) {

        Parent parent = parentRepository.findById(
                request.parentId()
        ).orElseThrow(() ->
                new ResourceNotFoundException("Parent not found"));

        Offering offering =
                offeringRepository
                        .findByIdWithSessions(
                                request.offeringId()
                        )
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Offering not found"
                                ));
        bookingRepository.findWithLockingByParentId(
                parent.getId()
        );

        boolean alreadyBooked =
                bookingRepository
                        .findByParentIdAndOfferingId(
                                parent.getId(),
                                offering.getId()
                        ).isPresent();

        if (alreadyBooked) {
            throw new DuplicateBookingException(
                    "Offering already booked"
            );
        }

        boolean hasConflict =
                hasConflict(parent, offering);

        if (hasConflict) {

            throw new ConflictException(
                    "Booking conflict detected"
            );
        }

        Booking booking = Booking.builder()
                .parent(parent)
                .offering(offering)
                .bookingStatus(BookingStatus.BOOKED)
                .build();

        Booking savedBooking =
                bookingRepository.save(booking);

        return new BookingResponse(
                savedBooking.getId(),
                offering.getId(),
                offering.getTitle(),
                savedBooking.getBookingStatus().name(),
                savedBooking.getBookedAt().toString()
        );
    }

    @Override
    public List<BookingResponse> getParentBookings(
            Long parentId
    ) {
        return List.of();
    }

    private boolean hasConflict(
            Parent parent,
            Offering newOffering
    ) {

        List<Booking> existingBookings =
                bookingRepository.findBookingsWithSessions(
                        parent.getId()
                );

        List<Session> newSessions =
                newOffering.getSessions();

        for (Booking booking : existingBookings) {

            List<Session> existingSessions =
                    booking.getOffering().getSessions();

            for (Session existingSession : existingSessions) {

                for (Session newSession : newSessions) {
                    boolean overlap =
                            existingSession.getStartTimeUtc()
                                    .isBefore(
                                            newSession.getEndTimeUtc()
                                    )
                                    &&
                                    newSession.getStartTimeUtc()
                                            .isBefore(
                                                    existingSession.getEndTimeUtc()
                                            );
                    if (overlap) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
