package com.example.document_review.controller;

import com.example.document_review.dto.AssetDto;
import com.example.document_review.service.AssetService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/asset")
public class AssetController {

    private AssetService assetService;

    public AssetController(AssetService assetService) {
        this.assetService = assetService;
    }

    @PostMapping
    public void save(@RequestBody AssetDto assetDto) {
        assetService.save(assetDto);
    }

}


