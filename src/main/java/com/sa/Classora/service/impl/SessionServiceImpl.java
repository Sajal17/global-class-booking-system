package com.sa.Classora.service.impl;

import com.sa.Classora.dto.request.CreateSessionRequest;
import com.sa.Classora.dto.response.SessionResponse;
import com.sa.Classora.entity.Offering;
import com.sa.Classora.entity.Session;
import com.sa.Classora.exception.ResourceNotFoundException;
import com.sa.Classora.repository.OfferingRepository;
import com.sa.Classora.repository.SessionRepository;
import com.sa.Classora.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {

    private final SessionRepository sessionRepository;

    private final OfferingRepository offeringRepository;

    @Override
    public SessionResponse createSession(CreateSessionRequest request) {

        Offering offering =
                offeringRepository.findById(
                                request.offeringId()
                        )
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Offering not found"
                                ));

        ZoneId zoneId =
                ZoneId.of(request.timezone());

        Instant startUtc =
                request.startTime()
                        .atZone(zoneId)
                        .toInstant();

        Instant endUtc =
                request.endTime()
                        .atZone(zoneId)
                        .toInstant();

        Session session = Session.builder()
                .offering(offering)
                .startTimeUtc(startUtc)
                .endTimeUtc(endUtc)
                .build();

        Session savedSession =
                sessionRepository.save(session);

        return mapToResponse(savedSession);
    }

    @Override
    public List<SessionResponse>
    getSessionsByOfferingId(
            Long offeringId
    ) {

        return sessionRepository
                .findByOfferingId(offeringId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private SessionResponse mapToResponse(
            Session session
    ) {

        return new SessionResponse(
                session.getId(),
                session.getStartTimeUtc()
                        .toString(),
                session.getEndTimeUtc()
                        .toString()
        );
    }
}
