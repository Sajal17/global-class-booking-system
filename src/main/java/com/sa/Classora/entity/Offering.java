package com.sa.Classora.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sa.Classora.emums.OfferingStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "offerings")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Offering {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private OfferingStatus status;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    // Many offerings belong to one course
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    @JsonIgnore
    private Course course;

    // Many offerings belong to one teacher
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id", nullable = false)
    @JsonIgnore
    private Teacher teacher;

    // One offering can have many sessions
    @OneToMany(mappedBy = "offering",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Session> sessions = new ArrayList<>();

    // One offering can have many bookings
    @OneToMany(mappedBy = "offering",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Booking> bookings = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        this.createdAt = Instant.now();

        if (this.status == null) {
            this.status = OfferingStatus.ACTIVE;
        }
    }
}
