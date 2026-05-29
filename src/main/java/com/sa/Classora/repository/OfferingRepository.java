package com.sa.Classora.repository;

import com.sa.Classora.entity.Offering;
import com.sa.Classora.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OfferingRepository extends JpaRepository<Offering,Long> {

    List<Offering> findByTeacher(Teacher teacher);

}
