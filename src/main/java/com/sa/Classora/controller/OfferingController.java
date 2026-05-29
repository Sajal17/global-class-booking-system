package com.sa.Classora.controller;

import com.sa.Classora.dto.request.CreateOfferingRequest;
import com.sa.Classora.dto.request.CreateSessionRequest;
import com.sa.Classora.dto.response.OfferingResponse;
import com.sa.Classora.service.OfferingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/offerings")
@RequiredArgsConstructor
public class OfferingController {

    private final OfferingService offeringService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OfferingResponse createOffering(@Valid @RequestBody CreateOfferingRequest request) {

        return offeringService.createOffering(request);
    }

    @PostMapping("/sessions")
    public void addSession(@Valid @RequestBody CreateSessionRequest request) {

        offeringService.addSession(request);
    }

    @GetMapping("/teacher/{teacherId}")
    public List<OfferingResponse> getTeacherOfferings(@PathVariable Long teacherId) {

        return offeringService.getTeacherOfferings(teacherId);
    }

    @GetMapping
    public List<OfferingResponse> getAllOfferings() {

        return offeringService.getAllOfferings();
    }
}
