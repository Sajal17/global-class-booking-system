package com.sa.Classora.service.impl;

import com.sa.Classora.dto.request.CreateCourseRequest;
import com.sa.Classora.dto.response.CourseResponse;
import com.sa.Classora.entity.Course;
import com.sa.Classora.exception.ResourceNotFoundException;
import com.sa.Classora.repository.CourseRepository;
import com.sa.Classora.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    @Override
    public CourseResponse createCourse(CreateCourseRequest request ) {

        Course course = Course.builder()
                .title(request.title())
                .description(
                        request.description()
                )
                .build();

        Course savedCourse =
                courseRepository.save(course);

        return mapToResponse(savedCourse);
    }

    @Override
    public CourseResponse getCourseById(
            Long id
    ) {

        Course course =
                courseRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Course not found"
                                ));

        return mapToResponse(course);
    }

    @Override
    public List<CourseResponse>
    getAllCourses() {

        return courseRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private CourseResponse mapToResponse(
            Course course
    ) {

        return new CourseResponse(
                course.getId(),
                course.getTitle(),
                course.getDescription()
        );
    }
}
