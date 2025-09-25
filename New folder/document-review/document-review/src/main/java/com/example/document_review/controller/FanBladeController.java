package com.example.document_review.controller;

import com.example.document_review.dto.FanBladeDto;
import com.example.document_review.exception.EntityNotFoundException;
import com.example.document_review.service.BasicPartService;
import com.example.document_review.service.FanBladeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/part/fanBlades")
public class FanBladeController {

    @Autowired
    private FanBladeService fanBladeService;

    public FanBladeController(FanBladeService fanBladeService) {
        this.fanBladeService = fanBladeService;
    }


    @GetMapping("/{fanBladeId}")
    public ResponseEntity<FanBladeDto> getById(@PathVariable Integer fanBladeId) throws Exception {
        FanBladeDto fanBladeDto = fanBladeService.getById(fanBladeId);
        if (fanBladeDto != null) {
            return ResponseEntity.ok(fanBladeDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<FanBladeDto>> findAll() throws Exception {
        return new ResponseEntity<>(fanBladeService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/save")
    public void save(@RequestBody FanBladeDto fanBladeDto) {
        fanBladeService.save(fanBladeDto);
    }

    @DeleteMapping("/{fanBladeId}")
    public ResponseEntity<String> delete(@PathVariable Integer fanBladeId) throws Exception {
        FanBladeDto existingFanBladeId = this.fanBladeService.getById(fanBladeId);
        if (existingFanBladeId != null) {
            this.fanBladeService.delete(fanBladeId);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{basicPartId}")
    public void update(@PathVariable Integer basicPartId) {
        fanBladeService.update(basicPartId);
    }
    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseStatusException handleEntityNotFoundException(EntityNotFoundException ex) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
    }
}
