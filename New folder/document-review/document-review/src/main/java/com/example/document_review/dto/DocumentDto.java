package com.example.document_review.dto;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Objects;

public class DocumentDto {
    private Integer documentId;
    private String documentName;
    private String documentType;
    private LocalDate documentDate;
    private Integer assetId;
    private String documentRoute;

    public DocumentDto(Integer documentId, String documentName, String documentType, LocalDate documentDate, Integer assetId, String documentRoute) {
        this.documentId = documentId;
        this.documentName = documentName;
        this.documentType = documentType;
        this.documentDate = documentDate;
        this.assetId = assetId;
        this.documentRoute = documentRoute;
    }

    public DocumentDto() {
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

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public Integer getAssetId() {
        return assetId;
    }

    public void setAssetId(Integer assetId) {
        this.assetId = assetId;
    }


    @Override
    public String toString() {
        return "DocumentDto{" +
                "documentId=" + documentId +
                ", documentName='" + documentName + '\'' +
                ", documentType='" + documentType + '\'' +
                ", documentDate=" + documentDate +
                ", assetId=" + assetId +
                ", documentRoute='" + documentRoute + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocumentDto that = (DocumentDto) o;
        return Objects.equals(documentId, that.documentId) && Objects.equals(documentName, that.documentName) && Objects.equals(documentType, that.documentType) && Objects.equals(documentDate, that.documentDate) && Objects.equals(assetId, that.assetId) && Objects.equals(documentRoute, that.documentRoute);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documentId, documentName, documentType, documentDate, assetId, documentRoute);
    }

}
