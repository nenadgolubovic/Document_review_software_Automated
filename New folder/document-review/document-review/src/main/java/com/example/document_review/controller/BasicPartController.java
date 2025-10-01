package com.example.document_review.controller;

import com.example.document_review.dto.BasicPartDto;
import com.example.document_review.exception.EntityNotFoundException;
import com.example.document_review.service.BasicPartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/part/basic")
public class BasicPartController {

    @Autowired
    private BasicPartService basicPartService;

    public BasicPartController(BasicPartService basicPartService) {
        this.basicPartService = basicPartService;
    }


    @GetMapping("/{basicPartId}")
    public ResponseEntity<BasicPartDto> getById(@PathVariable Integer basicPartId) throws Exception {
        BasicPartDto dto = basicPartService.getById(basicPartId);
        if (dto != null) {
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<BasicPartDto>> findAll() throws Exception {
        return new ResponseEntity<>(basicPartService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/save")
    public void save(@RequestBody BasicPartDto basicPartDto) {
        basicPartService.save(basicPartDto);
    }

    @DeleteMapping("/{basicPartId}")
    public ResponseEntity<String> delete(@PathVariable Integer basicPartId) throws Exception {
        BasicPartDto existingBasicPartId = this.basicPartService.getById(basicPartId);
        if (existingBasicPartId != null) {
            this.basicPartService.delete(basicPartId);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{basicPartId}")
    public void update(@PathVariable Integer basicPartId) {
        basicPartService.update(basicPartId);
    }
    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseStatusException handleEntityNotFoundException(EntityNotFoundException ex) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
    }


}
