package com.sa.Classora.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
@Entity
@Table(name = "sessions")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Store all times in UTC
    @Column(name = "start_time_utc", nullable = false)
    private Instant startTimeUtc;

    @Column(name = "end_time_utc", nullable = false)
    private Instant endTimeUtc;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    // Many sessions belong to one offering
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "offering_id", nullable = false)
    @JsonIgnore
    private Offering offering;

    @PrePersist
    public void prePersist() {
        this.createdAt = Instant.now();
    }
}
