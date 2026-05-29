package com.sa.Classora.controller;

import com.sa.Classora.dto.request.CreateTeacherRequest;
import com.sa.Classora.dto.response.TeacherResponse;
import com.sa.Classora.entity.Teacher;
import com.sa.Classora.service.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/teachers")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TeacherResponse createTeacher(
            @Valid
            @RequestBody
            CreateTeacherRequest request
    ) {

        return teacherService.createTeacher(request);
    }

    @GetMapping("/{teacherId}")
    public Teacher getTeacherById(
            @PathVariable Long teacherId
    ) {

        return teacherService.getTeacherById(teacherId);
    }

    @GetMapping
    public List<Teacher> getAllTeachers() {

        return teacherService.getAllTeachers();
    }
}
