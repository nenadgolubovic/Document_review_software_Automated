package com.example.document_review.entity;
import java.nio.file.Path;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer documentId;
    private String documentName;
    private String documentType;
    private LocalDate documentDate;
    private Integer assetId;
    private String documentRoute;

    public Document(Integer documentId, String documentName, String documentType, LocalDate documentDate, Integer assetId,String documentRoute) {
        this.documentId = documentId;
        this.documentName = documentName;
        this.documentType = documentType;
        this.documentDate = documentDate;
        this.assetId = assetId;
        this.documentRoute = documentRoute;
    }

    public Document() {
    }
    public String getDocumentRoute() {
        return documentRoute;
    }

    public void setDocumentRoute(String documentRoute) {
        this.documentRoute = documentRoute;
    }

    public Integer getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Integer documentId) {
        this.documentId = documentId;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public LocalDate getDocumentDate() {
        return documentDate;
    }

    public void setDocumentDate(LocalDate documentDate) {
        this.documentDate = documentDate;
    }

    public Integer getAssetId() {
        return assetId;
    }

    public void setAssetId(Integer assetId) {
        this.assetId = assetId;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Document document = (Document) o;
        return Objects.equals(documentId, document.documentId) && Objects.equals(documentName, document.documentName) && Objects.equals(documentType, document.documentType) && Objects.equals(documentDate, document.documentDate) && Objects.equals(assetId, document.assetId) && Objects.equals(documentRoute, document.documentRoute);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documentId, documentName, documentType, documentDate, assetId, documentRoute);
    }

    @Override
    public String toString() {
        return "Document{" +
                "documentId=" + documentId +
                ", documentName='" + documentName + '\'' +
                ", documentType='" + documentType + '\'' +
                ", documentDate=" + documentDate +
                ", assetId=" + assetId +
                ", documentRoute='" + documentRoute + '\'' +
                '}';
    }
}
