package com.sa.Classora.service;

import com.sa.Classora.dto.request.CreateSessionRequest;
import com.sa.Classora.dto.response.SessionResponse;

import java.util.List;

public interface SessionService {

    SessionResponse createSession(CreateSessionRequest request);

    List<SessionResponse> getSessionsByOfferingId(Long offeringId);
}
