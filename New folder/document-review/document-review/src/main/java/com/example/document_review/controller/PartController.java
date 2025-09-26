package com.example.document_review.controller;
import com.example.document_review.dto.BasicPartDto;
import com.example.document_review.dto.PartDto;
import com.example.document_review.exception.EntityNotFoundException;
import com.example.document_review.service.PartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
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

    @GetMapping("/all")
    public ResponseEntity<List<PartDto>> findAll() throws Exception {
        return new ResponseEntity<>(partService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/getById")
    public PartDto getById(@RequestParam int id) {
        return partService.getById(id);
    }
    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseStatusException handleEntityNotFoundException(EntityNotFoundException ex) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
    }
}
