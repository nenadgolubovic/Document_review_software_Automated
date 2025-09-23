package com.example.document_review.controller;
import com.example.document_review.dto.PartDto;
import com.example.document_review.service.PartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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

    @GetMapping
    public List<PartDto> getAll() {
     return partService.getAll();
    }

    @GetMapping
    public PartDto getById(@RequestParam int id) {
        return partService.getById(id);
    }
}
