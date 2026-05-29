package com.sa.Classora.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sa.Classora.emums.BookingStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
@Entity
@Table(
        name = "bookings",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_parent_offering",
                        columnNames = {
                               "parent_id",
                                "offering_id"
                        }
                )
        }
        )
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "booking_status",
            nullable = false,
            length = 30)
    private BookingStatus bookingStatus;

    @Column(name = "booked_at",
            nullable = false)
    private Instant bookedAt;

    // Many bookings belong to one parent
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id",
            nullable = false)
    @JsonIgnore
    private Parent parent;

    // Many bookings belong to one offering
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "offering_id",
            nullable = false)
    @JsonIgnore
    private Offering offering;

    @PrePersist
    public void prePersist() {

        this.bookedAt = Instant.now();

        if (this.bookingStatus == null) {
            this.bookingStatus = BookingStatus.BOOKED;
        }
    }
}
