package com.sa.Classora.dto.response;

public record TeacherResponse(
        Long id,

        String name,

        String email,

        String timezone
) {
}
