package com.sa.Classora.repository;

import com.sa.Classora.entity.Booking;
import com.sa.Classora.entity.Parent;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking,Long> {

    List<Booking> findByParent(Parent parent);

    Optional<Booking> findByParentIdAndOfferingId(
            Long parentId,
            Long offeringId
    );

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<Booking> findWithLockingByParentId(Long parentId);

    @Query("""
    SELECT DISTINCT b
    FROM Booking b
    JOIN FETCH b.offering o
    JOIN FETCH o.sessions s
    WHERE b.parent.id = :parentId
""")
    List<Booking> findBookingsWithSessions(
            @Param("parentId") Long parentId
    );
}
