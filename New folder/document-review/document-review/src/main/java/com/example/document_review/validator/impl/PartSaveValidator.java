package com.example.document_review.validator.impl;

import com.example.document_review.dto.PartDto;
import com.example.document_review.exception.ValidationException;
import com.example.document_review.validator.Validator;

public class PartSaveValidator implements Validator <PartDto> {
    @Override
    public void validate(PartDto partDto) throws ValidationException {
        if (partDto.getPartNumber() == null || partDto.getPartNumber().trim().isEmpty() || partDto.getSerialNumber() == null || partDto.getSerialNumber().trim().isEmpty() ) {
            throw new ValidationException("Part number or serial number are required, please fill");
        }
    }
}
