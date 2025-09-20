package com.example.document_review.controller;
import com.example.document_review.dto.PartDto;
import com.example.document_review.service.PartService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/part")
public class PartController {

    private PartService partService;


    public PartController(PartService partService) {
        this.partService = partService;
    }

    @PostMapping
    public void save(@RequestBody PartDto partDto) {
        partService.save(partDto);
    }

}
