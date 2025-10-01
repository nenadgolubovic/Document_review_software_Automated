package com.example.document_review.entity;

import jakarta.persistence.*;
import lombok.Builder;

import java.time.LocalDate;
import java.util.Objects;


@Entity
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer documentId;
    private String documentName;
    @ManyToOne
    @JoinColumn(name = "part_id", insertable = false, updatable = false)
    private Part part;
    @Column(name = "part_id")
    private Integer partId;
    private String documentRoute;
//    @OneToMany(mappedBy = "document", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Comment> comments = new ArrayList<>();
    private LocalDate documentDate;

    @Builder
    public Document(Integer documentId, String documentName, Part part, String documentRoute,
//                    List<Comment> comments,
                    LocalDate documentDate, Integer partId) {
        this.documentId = documentId;
        this.documentName = documentName;
        this.part = part;
        this.documentRoute = documentRoute;
//        this.comments = comments;
        this.documentDate = documentDate;
        this.partId = partId;
    }

    public Document() {
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

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    public String getDocumentRoute() {
        return documentRoute;
    }

    public void setDocumentRoute(String documentRoute) {
        this.documentRoute = documentRoute;
    }

//    public List<Comment> getComments() {
//        return comments;
//    }
//
//    public void setComments(List<Comment> comments) {
//        this.comments = comments;
//    }

    public LocalDate getDocumentDate() {
        return documentDate;
    }

    public void setDocumentDate(LocalDate documentDate) {
        this.documentDate = documentDate;
    }

    public Integer getPartId() {
        return partId;
    }

    public void setPartId(Integer partId) {
        this.partId = partId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Document document = (Document) o;
        return Objects.equals(documentId, document.documentId) && Objects.equals(documentName, document.documentName) && Objects.equals(part, document.part) && Objects.equals(documentRoute, document.documentRoute) && Objects.equals(documentDate, document.documentDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documentId, documentName, part, documentRoute, documentDate);
    }

    @Override
    public String toString() {
        return "Document{" +
                "documentId=" + documentId +
                ", documentName='" + documentName + '\'' +
                ", part=" + part +
                ", documentRoute='" + documentRoute + '\'' +
//                ", comments=" + comments +
                ", documentDate=" + documentDate +
                '}';
    }
}
