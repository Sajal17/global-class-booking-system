package com.sa.Classora.repository;

import com.sa.Classora.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher,Long> {

    Optional<Teacher> findByEmail(String email);

}
