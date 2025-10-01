package com.example.document_review.entity;

import jakarta.persistence.Entity;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Entity
public class BasicPart extends Part {

    public BasicPart() {
        // Default constructor required by frameworks or for inheritance
    }
}
