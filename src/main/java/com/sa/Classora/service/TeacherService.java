package com.sa.Classora.service;

import com.sa.Classora.dto.request.CreateTeacherRequest;
import com.sa.Classora.dto.response.TeacherResponse;
import com.sa.Classora.entity.Teacher;

import java.util.List;

public interface TeacherService {

    TeacherResponse createTeacher(
            CreateTeacherRequest request
    );

    Teacher getTeacherById(
            Long teacherId
    );

    List<Teacher> getAllTeachers();
}
