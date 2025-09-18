package com.example.document_review.controller;

import org.springframework.web.bind.annotation.RestController;

@RestController("/part")
public class PartController {

    private PartService partService;


    public PartController(PartService partService) {
        this.partService = partService;
    }
}
