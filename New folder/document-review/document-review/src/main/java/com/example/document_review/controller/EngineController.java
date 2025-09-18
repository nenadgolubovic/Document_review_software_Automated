package com.example.document_review.controller;

import com.example.document_review.dto.EngineDto;
import com.example.document_review.repository.impl.EngineRepository;
import com.example.document_review.service.EngineService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/engine")
public class EngineController {

    private EngineService engineService;

    public EngineController(EngineService engineService) {
        this.engineService = engineService;
    }

    @PostMapping("/post")
    public void save(@RequestBody EngineDto engineDto) {
        engineService.save(engineDto);
    }


}
