package com.sa.Classora.service.impl;

import com.sa.Classora.dto.request.CreateOfferingRequest;
import com.sa.Classora.dto.request.CreateSessionRequest;
import com.sa.Classora.dto.response.OfferingResponse;
import com.sa.Classora.emums.OfferingStatus;
import com.sa.Classora.entity.Course;
import com.sa.Classora.entity.Offering;
import com.sa.Classora.entity.Session;
import com.sa.Classora.entity.Teacher;
import com.sa.Classora.exception.ResourceNotFoundException;
import com.sa.Classora.repository.CourseRepository;
import com.sa.Classora.repository.OfferingRepository;
import com.sa.Classora.repository.SessionRepository;
import com.sa.Classora.repository.TeacherRepository;
import com.sa.Classora.service.OfferingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OfferingServiceImpl implements OfferingService {

    private final OfferingRepository offeringRepository;
    private final TeacherRepository teacherRepository;
    private final CourseRepository courseRepository;
    private final SessionRepository sessionRepository;

    @Override
    public OfferingResponse createOffering(
            CreateOfferingRequest request
    ) {

        Teacher teacher = teacherRepository.findById(
                request.teacherId()
        ).orElseThrow(() ->
                new ResourceNotFoundException("Teacher not found"));

        Course course = courseRepository.findById(
                request.courseId()
        ).orElseThrow(() ->
                new ResourceNotFoundException("Course not found"));

        Offering offering = Offering.builder()
                .title(request.title())
                .teacher(teacher)
                .course(course)
                .status(OfferingStatus.ACTIVE)
                .build();

        Offering savedOffering =
                offeringRepository.save(offering);

        return new OfferingResponse(
                savedOffering.getId(),
                savedOffering.getTitle(),
                course.getTitle(),
                teacher.getName(),
                savedOffering.getStatus().name(),
                List.of()
        );
    }

    @Override
    public void addSession(
            CreateSessionRequest request
    ) {

        Offering offering = offeringRepository.findById(
                request.offeringId()
        ).orElseThrow(() ->
                new ResourceNotFoundException("Offering not found"));

        ZonedDateTime startZoned =
                request.startTime()
                        .atZone(
                                ZoneId.of(request.timezone())
                        );

        ZonedDateTime endZoned =
                request.endTime()
                        .atZone(
                                ZoneId.of(request.timezone())
                        );

        Session session = Session.builder()
                .offering(offering)
                .startTimeUtc(startZoned.toInstant())
                .endTimeUtc(endZoned.toInstant())
                .build();

        sessionRepository.save(session);
    }

    @Override
    public List<OfferingResponse> getTeacherOfferings(
            Long teacherId
    ) {
        return List.of();
    }

    @Override
    public List<OfferingResponse> getAllOfferings() {
        return List.of();
    }

}
