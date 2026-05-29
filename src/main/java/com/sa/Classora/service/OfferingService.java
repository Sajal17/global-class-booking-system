package com.sa.Classora.service;

import com.sa.Classora.dto.request.CreateOfferingRequest;
import com.sa.Classora.dto.request.CreateSessionRequest;
import com.sa.Classora.dto.response.OfferingResponse;

import java.util.List;

public interface OfferingService {

    OfferingResponse createOffering(
            CreateOfferingRequest request
    );

    void addSession(
            CreateSessionRequest request
    );

    List<OfferingResponse> getTeacherOfferings(
            Long teacherId
    );

    List<OfferingResponse> getAllOfferings();
}
