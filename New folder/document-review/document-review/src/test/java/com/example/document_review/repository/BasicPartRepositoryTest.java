package com.example.document_review.repository;

import com.example.document_review.dto.BasicPartDto;
import com.example.document_review.entity.BasicPart;
import com.example.document_review.entity.enums.PartType;
import com.example.document_review.repository.impl.BasicPartRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class BasicPartRepositoryTest {

    BasicPartRepository basicPartRepository;


    @PersistenceContext
    EntityManager entityManager;

    private BasicPart basicPart1;
    private BasicPart basicPart2;

    @BeforeEach
    void setUp() {
        basicPartRepository = new BasicPartRepository();
        ReflectionTestUtils.setField(basicPartRepository, "entityManager", entityManager);
        basicPart1 = BasicPart.builder()
                .partNumber("PartNumberTest")
                .name("NameTest")
                .description("DescriptionTest")
                .serialNumber("SerialNumberTest")
                .type(PartType.BASIC)
                .cyclesSinceNew("CycleSinceNewTest")
                .timeSinceNew("TimeSinceNewTest")
                .build();
        basicPart2 = BasicPart.builder()
                .partNumber("PartNumberTest2")
                .name("NameTest2")
                .description("DescriptionTest2")
                .serialNumber("SerialNumberTest2")
                .type(PartType.BASIC)
                .cyclesSinceNew("CycleSinceNewTest2")
                .timeSinceNew("TimeSinceNewTest2")
                .build();
    }

    @Test
    public void basicPartRepositorySave() {

        basicPartRepository.save(basicPart1);

        BasicPart savedBasicPart = entityManager.find(BasicPart.class, basicPart1.getPartId());

        Assertions.assertThat(savedBasicPart).isNotNull();
        Assertions.assertThat(savedBasicPart.getPartNumber()).isEqualTo(basicPart1.getPartNumber());
        Assertions.assertThat(savedBasicPart.getName()).isEqualTo(basicPart1.getName());
        Assertions.assertThat(savedBasicPart.getDescription()).isEqualTo(basicPart1.getDescription());
        Assertions.assertThat(savedBasicPart.getSerialNumber()).isEqualTo(basicPart1.getSerialNumber());
        Assertions.assertThat(savedBasicPart.getType()).isEqualTo(basicPart1.getType());
        Assertions.assertThat(savedBasicPart.getCyclesSinceNew()).isEqualTo(basicPart1.getCyclesSinceNew());
        Assertions.assertThat(savedBasicPart.getTimeSinceNew()).isEqualTo(basicPart1.getTimeSinceNew());

    }

    @Test
    public void basicPartRepositoryFindAllReturnFanBlades() throws Exception {

        basicPartRepository.save(basicPart1);
        basicPartRepository.save(basicPart2);

        List<BasicPart> basicParts = basicPartRepository.findAll();

        Assertions.assertThat(basicParts.size()).isEqualTo(2);
        Assertions.assertThat(basicParts.get(0)).isEqualTo(basicPart1);
        Assertions.assertThat(basicParts.get(0).getPartId()).isEqualTo(basicPart1.getPartId());
        Assertions.assertThat(basicParts.get(0).getName()).isEqualTo(basicPart1.getName());
        Assertions.assertThat(basicParts.get(0).getDescription()).isEqualTo(basicPart1.getDescription());
        Assertions.assertThat(basicParts.get(0).getSerialNumber()).isEqualTo(basicPart1.getSerialNumber());
        Assertions.assertThat(basicParts.get(0).getType()).isEqualTo(basicPart1.getType());
        Assertions.assertThat(basicParts.get(0).getCyclesSinceNew()).isEqualTo(basicPart1.getCyclesSinceNew());
        Assertions.assertThat(basicParts.get(0).getTimeSinceNew()).isEqualTo(basicPart1.getTimeSinceNew());

        Assertions.assertThat(basicParts.get(1)).isEqualTo(basicPart2);
        Assertions.assertThat(basicParts.get(1)).isEqualTo(basicPart2);
        Assertions.assertThat(basicParts.get(1).getPartId()).isEqualTo(basicPart2.getPartId());
        Assertions.assertThat(basicParts.get(1).getName()).isEqualTo(basicPart2.getName());
        Assertions.assertThat(basicParts.get(1).getDescription()).isEqualTo(basicPart2.getDescription());
        Assertions.assertThat(basicParts.get(1).getSerialNumber()).isEqualTo(basicPart2.getSerialNumber());
        Assertions.assertThat(basicParts.get(1).getType()).isEqualTo(basicPart2.getType());
        Assertions.assertThat(basicParts.get(1).getCyclesSinceNew()).isEqualTo(basicPart2.getCyclesSinceNew());
        Assertions.assertThat(basicParts.get(1).getTimeSinceNew()).isEqualTo(basicPart2.getTimeSinceNew());

    }

    @Test
    public void basicPartRepositoryFindByIdReturnFanBlade() {

        basicPartRepository.save(basicPart1);
        basicPartRepository.save(basicPart2);

        BasicPart foundBasicPart = basicPartRepository.findById(basicPart1.getPartId());

        Assertions.assertThat(foundBasicPart).isNotNull();
        Assertions.assertThat(foundBasicPart).isEqualTo(basicPart1);
    }

    @Test
    public void fanBladeRepositoryDelete() throws Exception {


        basicPartRepository.save(basicPart1);
        basicPartRepository.save(basicPart2);
        basicPartRepository.delete(basicPart1.getPartId());

        List<BasicPart> basicParts = basicPartRepository.findAll();

        Assertions.assertThat(basicParts.size()).isEqualTo(1);
        Assertions.assertThat(basicParts.get(0)).isEqualTo(basicPart2);


    }
}
