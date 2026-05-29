package com.sa.Classora.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateOfferingRequest (

        @NotNull(message = "Course ID is required")
        Long courseId,

        @NotNull(message = "Teacher ID is required")
        Long teacherId,

        @NotBlank(message = "Title is required")
        @Size(
                min = 3,
                max = 100,
                message = "Title must be between 3 and 100 characters"
        )
        String title
){ }
