package com.example.document_review.repository;

import com.example.document_review.entity.Document;
import com.example.document_review.entity.enums.PartType;
import com.example.document_review.entity.FanBlade;
import com.example.document_review.repository.impl.DocumentRepository;
import com.example.document_review.repository.impl.FanBladeRepository;
import jakarta.persistence.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.util.ReflectionTestUtils;
import java.time.LocalDate;
import java.util.List;


@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class DocumentRepositoryTest {

    DocumentRepository documentRepository;
    FanBladeRepository fanBladeRepository;

    private FanBlade fanBlade;
    private Document document;
    private Document document2;

    @BeforeEach
    void setUp() {
        documentRepository = new DocumentRepository();
        fanBladeRepository = new FanBladeRepository();
        ReflectionTestUtils.setField(documentRepository, "entityManager", entityManager);
        ReflectionTestUtils.setField(fanBladeRepository, "entityManager", entityManager);
        fanBlade = FanBlade.builder()
                .partNumber("PartNumberTest")
                .description("DescriptionTest")
                .serialNumber("SerialNumberTest")
                .type(PartType.FAN_BLADE)
                .cyclesSinceNew("CycleSinceNewTest")
                .timeSinceNew("TimeSinceNewTest")
                .momentWeight("MomentWeightTest").build();


        document = Document.builder()
                .documentName("DocumentNameTest")
                .partId(fanBlade.getPartId())
                .documentRoute("DocumentRouteTest.pdf")
                .documentDate(LocalDate.now())
                .build();

        document2 = Document.builder()
                .documentName("DocumentNameTest2")
                .partId(fanBlade.getPartId())
                .documentRoute("DocumentRouteTest2.pdf")
                .documentDate(LocalDate.now())
                .build();
    }
    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void documentRepositorySave() {

        fanBladeRepository.save(fanBlade);
        documentRepository.save(document);

        Document foundDocument = entityManager.find(Document.class, document.getDocumentId());

        Assertions.assertThat(foundDocument).isNotNull();
        Assertions.assertThat(foundDocument.getDocumentName()).isEqualTo(document.getDocumentName());
        Assertions.assertThat(foundDocument.getPartId()).isEqualTo(document.getPartId());
        Assertions.assertThat(foundDocument.getDocumentRoute()).isEqualTo(document.getDocumentRoute());
        Assertions.assertThat(foundDocument.getDocumentDate()).isEqualTo(document.getDocumentDate());


    }
    @Test
    public void documentRepositoryFindByIdReturnDocument() {

        fanBladeRepository.save(fanBlade);
        documentRepository.save(document);

        Document foundDocument = documentRepository.findById(document.getDocumentId());
        Assertions.assertThat(foundDocument).isNotNull();
        Assertions.assertThat(foundDocument.getDocumentName()).isEqualTo(document.getDocumentName());
        Assertions.assertThat(foundDocument.getPartId()).isEqualTo(document.getPartId());
        Assertions.assertThat(foundDocument.getDocumentRoute()).isEqualTo(document.getDocumentRoute());
    }
    @Test
    public void documentRepositoryFindAllReturnAllDocuments() throws Exception {

        fanBladeRepository.save(fanBlade);
        documentRepository.save(document);
        documentRepository.save(document2);

        List<Document> foundDocuments = documentRepository.findAll();

        Assertions.assertThat(foundDocuments.size()).isEqualTo(2);
        Assertions.assertThat(foundDocuments.get(0)).isEqualTo(document);
        Assertions.assertThat(foundDocuments.get(1)).isEqualTo(document2);
    }
    @Test
    public void documentRepositoryDelete() {

        fanBladeRepository.save(fanBlade);
        documentRepository.save(document);

        documentRepository.delete(document.getDocumentId());

        Assertions.assertThat(documentRepository.findById(document.getDocumentId())).isEqualTo(null);

    }




}
