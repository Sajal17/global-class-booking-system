package com.sa.Classora.service;

import com.sa.Classora.dto.request.CreateCourseRequest;
import com.sa.Classora.dto.response.CourseResponse;

import java.util.List;

public interface CourseService {

    CourseResponse createCourse(CreateCourseRequest request );

    CourseResponse getCourseById(Long id);

    List<CourseResponse> getAllCourses();

}
