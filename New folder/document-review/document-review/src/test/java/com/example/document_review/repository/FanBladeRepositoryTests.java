package com.example.document_review.repository;

import com.example.document_review.entity.Comment;
import com.example.document_review.entity.Document;
import com.example.document_review.entity.Enums.PartType;
import com.example.document_review.entity.FanBlade;
import com.example.document_review.entity.Part;
import com.example.document_review.repository.impl.FanBladeRepository;
import jakarta.persistence.CascadeType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PersistenceContext;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.util.ReflectionTestUtils;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class FanBladeRepositoryTests {

    FanBladeRepository fanBladeRepository;


    @PersistenceContext
    EntityManager entityManager;

    @BeforeEach
    void setUp() {
        fanBladeRepository = new FanBladeRepository();
        ReflectionTestUtils.setField(fanBladeRepository, "entityManager", entityManager);
    }

    @Test
    public void FanBladeRepository_Save() {

        FanBlade fanBlade = FanBlade.builder()
                .partNumber("PartNumberTest")
                .name("NameTest")
                .description("DescriptionTest")
                .serialNumber("SerialNumberTest")
                .type(PartType.FanBlade)
                .cyclesSinceNew("CycleSinceNewTest")
                .timeSinceNew("TimeSinceNewTest")
                .momentWeight("MomentWeightTest").build();

        fanBladeRepository.save(fanBlade);

        FanBlade savedFanBlade = entityManager.find(FanBlade.class, fanBlade.getPartId());

        Assertions.assertThat(savedFanBlade).isNotNull();
        Assertions.assertThat(savedFanBlade.getPartNumber()).isEqualTo(fanBlade.getPartNumber());
        Assertions.assertThat(savedFanBlade.getName()).isEqualTo(fanBlade.getName());
        Assertions.assertThat(savedFanBlade.getDescription()).isEqualTo(fanBlade.getDescription());
        Assertions.assertThat(savedFanBlade.getSerialNumber()).isEqualTo(fanBlade.getSerialNumber());
        Assertions.assertThat(savedFanBlade.getType()).isEqualTo(fanBlade.getType());
        Assertions.assertThat(savedFanBlade.getCyclesSinceNew()).isEqualTo(fanBlade.getCyclesSinceNew());
        Assertions.assertThat(savedFanBlade.getTimeSinceNew()).isEqualTo(fanBlade.getTimeSinceNew());
        Assertions.assertThat(savedFanBlade.getMomentWeight()).isEqualTo(fanBlade.getMomentWeight());



    }

    @Test
    public void FanBladeRepository_FindAll_ReturnFanBlades() throws Exception {
        FanBlade fanBlade1 = FanBlade.builder()
                .partNumber("PartNumberTest")
                .name("NameTest")
                .description("DescriptionTest")
                .serialNumber("SerialNumberTest")
                .type(PartType.FanBlade)
                .cyclesSinceNew("CycleSinceNewTest")
                .timeSinceNew("TimeSinceNewTest")
                .momentWeight("MomentWeightTest").build();
        FanBlade fanBlade2 = FanBlade.builder()
                .partNumber("PartNumberTest2")
                .name("NameTest2")
                .description("DescriptionTest2")
                .serialNumber("SerialNumberTest2")
                .type(PartType.FanBlade)
                .cyclesSinceNew("CycleSinceNewTest2")
                .timeSinceNew("TimeSinceNewTest2")
                .momentWeight("MomentWeightTest2").build();

        fanBladeRepository.save(fanBlade1);
        fanBladeRepository.save(fanBlade2);

        List<FanBlade> fanBlades = fanBladeRepository.findAll();

        Assertions.assertThat(fanBlades.size()).isEqualTo(2);
        Assertions.assertThat(fanBlades.get(0)).isEqualTo(fanBlade1);
        Assertions.assertThat(fanBlades.get(0).getPartId()).isEqualTo(fanBlade1.getPartId());
        Assertions.assertThat(fanBlades.get(0).getName()).isEqualTo(fanBlade1.getName());
        Assertions.assertThat(fanBlades.get(0).getDescription()).isEqualTo(fanBlade1.getDescription());
        Assertions.assertThat(fanBlades.get(0).getSerialNumber()).isEqualTo(fanBlade1.getSerialNumber());
        Assertions.assertThat(fanBlades.get(0).getType()).isEqualTo(fanBlade1.getType());
        Assertions.assertThat(fanBlades.get(0).getCyclesSinceNew()).isEqualTo(fanBlade1.getCyclesSinceNew());
        Assertions.assertThat(fanBlades.get(0).getTimeSinceNew()).isEqualTo(fanBlade1.getTimeSinceNew());
        Assertions.assertThat(fanBlades.get(0).getMomentWeight()).isEqualTo(fanBlade1.getMomentWeight());


        Assertions.assertThat(fanBlades.get(1)).isEqualTo(fanBlade2);
        Assertions.assertThat(fanBlades.get(1)).isEqualTo(fanBlade2);
        Assertions.assertThat(fanBlades.get(1).getPartId()).isEqualTo(fanBlade2.getPartId());
        Assertions.assertThat(fanBlades.get(1).getName()).isEqualTo(fanBlade2.getName());
        Assertions.assertThat(fanBlades.get(1).getDescription()).isEqualTo(fanBlade2.getDescription());
        Assertions.assertThat(fanBlades.get(1).getSerialNumber()).isEqualTo(fanBlade2.getSerialNumber());
        Assertions.assertThat(fanBlades.get(1).getType()).isEqualTo(fanBlade2.getType());
        Assertions.assertThat(fanBlades.get(1).getCyclesSinceNew()).isEqualTo(fanBlade2.getCyclesSinceNew());
        Assertions.assertThat(fanBlades.get(1).getTimeSinceNew()).isEqualTo(fanBlade2.getTimeSinceNew());
        Assertions.assertThat(fanBlades.get(1).getMomentWeight()).isEqualTo(fanBlade2.getMomentWeight());
    }

    @Test
    public void FanBladeRepository_FindById_ReturnFanBlade() {
        FanBlade fanBlade1 = FanBlade.builder()
                .partNumber("PartNumberTest")
                .name("NameTest")
                .description("DescriptionTest")
                .serialNumber("SerialNumberTest")
                .type(PartType.FanBlade)
                .cyclesSinceNew("CycleSinceNewTest")
                .timeSinceNew("TimeSinceNewTest")
                .momentWeight("MomentWeightTest").build();
        FanBlade fanBlade2 = FanBlade.builder()
                .partNumber("PartNumberTest2")
                .name("NameTest2")
                .description("DescriptionTest2")
                .serialNumber("SerialNumberTest2")
                .type(PartType.FanBlade)
                .cyclesSinceNew("CycleSinceNewTest2")
                .timeSinceNew("TimeSinceNewTest2")
                .momentWeight("MomentWeightTest2").build();

        fanBladeRepository.save(fanBlade1);
        fanBladeRepository.save(fanBlade2);

        FanBlade foundFanBlade = fanBladeRepository.findById(fanBlade1.getPartId());

        Assertions.assertThat(foundFanBlade).isNotNull();
        Assertions.assertThat(foundFanBlade).isEqualTo(fanBlade1);
    }

    @Test
    public void FanBladeRepository_Delete() throws Exception {
        FanBlade fanBlade1 = FanBlade.builder()
                .partNumber("PartNumberTest")
                .name("NameTest")
                .description("DescriptionTest")
                .serialNumber("SerialNumberTest")
                .type(PartType.FanBlade)
                .cyclesSinceNew("CycleSinceNewTest")
                .timeSinceNew("TimeSinceNewTest")
                .momentWeight("MomentWeightTest").build();
        FanBlade fanBlade2 = FanBlade.builder()
                .partNumber("PartNumberTest2")
                .name("NameTest2")
                .description("DescriptionTest2")
                .serialNumber("SerialNumberTest2")
                .type(PartType.FanBlade)
                .cyclesSinceNew("CycleSinceNewTest2")
                .timeSinceNew("TimeSinceNewTest2")
                .momentWeight("MomentWeightTest2").build();

        fanBladeRepository.save(fanBlade1);
        fanBladeRepository.save(fanBlade2);
        fanBladeRepository.delete(fanBlade1.getPartId());

        List<FanBlade> fanBlades = fanBladeRepository.findAll();

        Assertions.assertThat(fanBlades.size()).isEqualTo(8);
        Assertions.assertThat(fanBlades.get(0)).isEqualTo(fanBlade2);


    }








}
