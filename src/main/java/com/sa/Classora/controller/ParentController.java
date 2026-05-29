package com.sa.Classora.controller;

import com.sa.Classora.dto.request.CreateParentRequest;
import com.sa.Classora.dto.response.ParentResponse;
import com.sa.Classora.entity.Parent;
import com.sa.Classora.service.ParentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/parents")
@RequiredArgsConstructor
public class ParentController {

    private final ParentService parentService;

    @PostMapping
    public ParentResponse createParent(
            @Valid @RequestBody
            CreateParentRequest request
    ) {

        return parentService.createParent(request);
    }

    @GetMapping("/{parentId}")
    public Parent getParentById(
            @PathVariable Long parentId
    ) {

        return parentService.getParentById(parentId);
    }

    @GetMapping
    public List<Parent> getAllParents() {

        return parentService.getAllParents();
    }
}
