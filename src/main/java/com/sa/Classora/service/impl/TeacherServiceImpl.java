package com.sa.Classora.service.impl;

import com.sa.Classora.dto.request.CreateTeacherRequest;
import com.sa.Classora.dto.response.TeacherResponse;
import com.sa.Classora.entity.Teacher;
import com.sa.Classora.exception.ResourceNotFoundException;
import com.sa.Classora.repository.TeacherRepository;
import com.sa.Classora.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    @Override
    public TeacherResponse createTeacher(
            CreateTeacherRequest request
    ) {

        Teacher teacher=Teacher.builder()
                .name(request.name())
                .email(request.email())
                .timezone(request.timezone())
                .build();

        Teacher savedTeacher=teacherRepository.save(teacher);

        return new TeacherResponse(
                savedTeacher.getId(),
                savedTeacher.getName(),
                savedTeacher.getEmail(),
                savedTeacher.getTimezone()
        );
    }

    @Override
    public Teacher getTeacherById(
            Long teacherId
    ) {

        return teacherRepository.findById(teacherId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Teacher not found"
                        ));
    }

    @Override
    public List<Teacher> getAllTeachers() {

        return teacherRepository.findAll();
    }
}
