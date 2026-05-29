package com.sa.Classora.service.impl;

import com.sa.Classora.dto.request.CreateParentRequest;
import com.sa.Classora.dto.response.ParentResponse;
import com.sa.Classora.entity.Parent;
import com.sa.Classora.exception.ResourceNotFoundException;
import com.sa.Classora.repository.ParentRepository;
import com.sa.Classora.service.ParentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParentServiceImpl implements ParentService {

    private final ParentRepository parentRepository;

    @Override
    public ParentResponse createParent(
            CreateParentRequest request
    ) {

        Parent parent=Parent.builder()
                .name(request.name())
                .email(request.email())
                .timezone(request.timezone())
                .build();

        Parent saveParent=parentRepository.save(parent);

        return new ParentResponse(
                saveParent.getId(),
                saveParent.getName(),
                saveParent.getEmail(),
                saveParent.getTimezone()
        );
    }

    @Override
    public Parent getParentById(
            Long parentId
    ) {

        return parentRepository.findById(parentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                 "Parent not found"
        ));
    }

    @Override
    public List<Parent> getAllParents() {

        return parentRepository.findAll();
    }


}
