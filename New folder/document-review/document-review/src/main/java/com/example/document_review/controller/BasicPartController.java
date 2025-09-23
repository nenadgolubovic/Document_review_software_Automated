package com.example.document_review.controller;

import com.example.document_review.dto.BasicPartDto;
import com.example.document_review.dto.CommentDto;
import com.example.document_review.dto.PartDto;
import com.example.document_review.entity.Part;
import com.example.document_review.service.BasicPartService;
import com.example.document_review.service.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/part/basic")
public class BasicPartController {

    @Autowired
    private BasicPartService basicPartService;

    public BasicPartController(BasicPartService basicPartService) {
        this.basicPartService = basicPartService;
    }


    @GetMapping("/{id}")
    public BasicPartDto getById(@PathVariable  int id) {
        return basicPartService.getById(id);

    }

    @GetMapping("/all")
    public ResponseEntity<List<BasicPartDto>> findAll() throws Exception {
        return new ResponseEntity<>(basicPartService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/save")
    public void save(@RequestBody BasicPartDto basicPartDto) {
        basicPartService.save(basicPartDto);
    }

}
