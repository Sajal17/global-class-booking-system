package com.sa.Classora.repository;

import com.sa.Classora.entity.Offering;
import com.sa.Classora.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OfferingRepository extends JpaRepository<Offering,Long> {

    List<Offering> findByTeacher(Teacher teacher);

    @Query("""
       SELECT DISTINCT o
       FROM Offering o
       LEFT JOIN FETCH o.sessions
       WHERE o.id = :offeringId
       """)
    Optional<Offering> findByIdWithSessions(
            @Param("offeringId") Long offeringId
    );
}
