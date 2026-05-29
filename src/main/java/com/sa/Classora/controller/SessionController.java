package com.sa.Classora.controller;

import com.sa.Classora.dto.request.CreateSessionRequest;
import com.sa.Classora.dto.response.SessionResponse;
import com.sa.Classora.service.SessionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sessions")
@RequiredArgsConstructor
public class SessionController {

    private final SessionService sessionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SessionResponse createSession(@Valid @RequestBody CreateSessionRequest request) {

        return sessionService.createSession(request);
    }

    @GetMapping("/offering/{offeringId}")
    public List<SessionResponse> getSessionsByOfferingId(@PathVariable Long offeringId) {

        return sessionService.getSessionsByOfferingId(offeringId );
    }

}
