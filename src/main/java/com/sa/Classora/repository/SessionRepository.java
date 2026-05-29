package com.sa.Classora.repository;

import com.sa.Classora.entity.Offering;
import com.sa.Classora.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SessionRepository extends JpaRepository<Session,Long> {

    List<Session> findByOffering(Offering offering);

}

