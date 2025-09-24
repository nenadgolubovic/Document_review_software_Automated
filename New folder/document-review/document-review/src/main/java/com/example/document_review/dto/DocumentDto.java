package com.example.document_review.dto;

import com.example.document_review.entity.Comment;
import com.example.document_review.entity.Part;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DocumentDto {
    private Integer documentId;
    private String documentName;
    private String documentRoute;
    private Part part;
    private List<Comment> comments = new ArrayList<>();
    private LocalDate documentDate;

    public DocumentDto(Integer documentId, String documentName, String documentRoute, Part part, List<Comment> comments, LocalDate documentDate) {
        this.documentId = documentId;
        this.documentName = documentName;
        this.documentRoute = documentRoute;
        this.part = part;
        this.comments = comments;
        this.documentDate = documentDate;
    }

    public DocumentDto() {
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

    public String getDocumentRoute() {
        return documentRoute;
    }

    public void setDocumentRoute(String documentRoute) {
        this.documentRoute = documentRoute;
    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public LocalDate getDocumentDate() {
        return documentDate;
    }

    public void setDocumentDate(LocalDate documentDate) {
        this.documentDate = documentDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocumentDto that = (DocumentDto) o;
        return Objects.equals(documentId, that.documentId) && Objects.equals(documentName, that.documentName) && Objects.equals(documentRoute, that.documentRoute) && Objects.equals(part, that.part) && Objects.equals(comments, that.comments) && Objects.equals(documentDate, that.documentDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documentId, documentName, documentRoute, part, comments, documentDate);
    }

    @Override
    public String toString() {
        return "DocumentDto{" +
                "documentId=" + documentId +
                ", documentName='" + documentName + '\'' +
                ", documentRoute='" + documentRoute + '\'' +
                ", part=" + part +
                ", comments=" + comments +
                ", documentDate=" + documentDate +
                '}';
    }
}
