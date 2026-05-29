package com.sa.Classora.repository;

import com.sa.Classora.entity.Parent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParentRepository extends JpaRepository<Parent,Long> {

    Optional<Parent> findByEmail(String email);

}
