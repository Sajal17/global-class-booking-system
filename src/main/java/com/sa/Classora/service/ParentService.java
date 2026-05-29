package com.sa.Classora.service;

import com.sa.Classora.dto.request.CreateParentRequest;
import com.sa.Classora.dto.response.ParentResponse;
import com.sa.Classora.entity.Parent;

import java.util.List;

public interface ParentService {

    ParentResponse createParent(
            CreateParentRequest request
    );

    Parent getParentById(
            Long parentId
    );

    List<Parent> getAllParents();
}
