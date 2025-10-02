package com.example.document_review.service;

import com.example.document_review.dto.BasicPartDto;
import com.example.document_review.entity.BasicPart;
import com.example.document_review.entity.Enums.PartType;
import com.example.document_review.mapper.impl.BasicPartMapper;
import com.example.document_review.repository.impl.BasicPartRepository;
import com.example.document_review.service.impl.BasicPartServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BasicPartServiceTest {

    @Mock
    BasicPartRepository basicPartRepository;

    @Mock
    BasicPartMapper basicPartMapper;

    @InjectMocks
    BasicPartServiceImpl basicPartServiceImpl;

    @Test
    public void BasicPartServiceSave() {
        BasicPart basicPart = BasicPart.builder()
                .partNumber("PartNumberTest")
                .name("NameTest")
                .description("DescriptionTest")
                .serialNumber("SerialNumberTest")
                .type(PartType.Basic)
                .cyclesSinceNew("CycleSinceNewTest")
                .timeSinceNew("TimeSinceNewTest")
                .build();
        BasicPartDto basicPartDto = BasicPartDto.builder()
                .partNumber("PartNumberTest")
                .name("NameTest")
                .description("DescriptionTest")
                .serialNumber("SerialNumberTest")
                .type(PartType.Basic)
                .cyclesSinceNew("CycleSinceNewTest")
                .timeSinceNew("TimeSinceNewTest")
                .build();

        when(basicPartMapper.toEntity(basicPartDto)).thenReturn(basicPart);

        basicPartServiceImpl.save(basicPartDto);

        verify(basicPartMapper, times(1)).toEntity(basicPartDto);

        verify(basicPartMapper, times(1)).toEntity(basicPartDto);
        verify(basicPartRepository, times(1)).save(basicPart);
    }

    @Test
    public void BasicPartServiceDelete() throws Exception {
        BasicPart basicPart = BasicPart.builder()
                .partNumber("PartNumberTest")
                .name("NameTest")
                .description("DescriptionTest")
                .serialNumber("SerialNumberTest")
                .type(PartType.Basic)
                .cyclesSinceNew("CycleSinceNewTest")
                .timeSinceNew("TimeSinceNewTest")
                .build();

        basicPartServiceImpl.delete(basicPart.getPartId());

        verify(basicPartRepository).delete(basicPart.getPartId());
    }

    @Test
    public void BasicPartServiceGetAllBasicPartDtos() throws Exception {
        BasicPart basicPart1 = BasicPart.builder()
                .partNumber("PartNumberTest1")
                .name("NameTest1")
                .description("DescriptionTest1")
                .serialNumber("SerialNumberTest1")
                .type(PartType.Basic)
                .cyclesSinceNew("CycleSinceNewTest1")
                .timeSinceNew("TimeSinceNewTest1")
                .build();
        BasicPart basicPart2 = BasicPart.builder()
                .partNumber("PartNumberTest2")
                .name("NameTest2")
                .description("DescriptionTest2")
                .serialNumber("SerialNumberTest2")
                .type(PartType.Basic)
                .cyclesSinceNew("CycleSinceNewTest2")
                .timeSinceNew("TimeSinceNewTest2")
                .build();
        BasicPartDto basicPartDto1 = BasicPartDto.builder()
                .partNumber("PartNumberTest1")
                .name("NameTest1")
                .description("DescriptionTest1")
                .serialNumber("SerialNumberTest1")
                .type(PartType.Basic)
                .cyclesSinceNew("CycleSinceNewTest1")
                .timeSinceNew("TimeSinceNewTest1")
                .build();
        BasicPartDto basicPartDto2 = BasicPartDto.builder()
                .partNumber("PartNumberTest2")
                .name("NameTest2")
                .description("DescriptionTest2")
                .serialNumber("SerialNumberTest2")
                .type(PartType.Basic)
                .cyclesSinceNew("CycleSinceNewTest2")
                .timeSinceNew("TimeSinceNewTest2")
                .build();

        when(basicPartRepository.findAll()).thenReturn(List.of(basicPart1,basicPart2));
        when(basicPartMapper.toDto(basicPart1)).thenReturn(basicPartDto1);
        when(basicPartMapper.toDto(basicPart2)).thenReturn(basicPartDto2);

        List<BasicPartDto> basicPartDtos = basicPartServiceImpl.getAll();

        assertThat(basicPartDtos).hasSize(2);


    }

    @Test
    public void BasicPartServiceGetByIdBasicPartDto() {
        BasicPart basicPart1 = BasicPart.builder()
                .partNumber("PartNumberTest1")
                .name("NameTest1")
                .description("DescriptionTest1")
                .serialNumber("SerialNumberTest1")
                .type(PartType.Basic)
                .cyclesSinceNew("CycleSinceNewTest1")
                .timeSinceNew("TimeSinceNewTest1")
                .build();
        BasicPart basicPart2 = BasicPart.builder()
                .partNumber("PartNumberTest2")
                .name("NameTest2")
                .description("DescriptionTest2")
                .serialNumber("SerialNumberTest2")
                .type(PartType.Basic)
                .cyclesSinceNew("CycleSinceNewTest2")
                .timeSinceNew("TimeSinceNewTest2")
                .build();
        BasicPartDto basicPartDto1 = BasicPartDto.builder()
                .partNumber("PartNumberTest1")
                .name("NameTest1")
                .description("DescriptionTest1")
                .serialNumber("SerialNumberTest1")
                .type(PartType.Basic)
                .cyclesSinceNew("CycleSinceNewTest1")
                .timeSinceNew("TimeSinceNewTest1")
                .build();
        BasicPartDto basicPartDto2 = BasicPartDto.builder()
                .partNumber("PartNumberTest2")
                .name("NameTest2")
                .description("DescriptionTest2")
                .serialNumber("SerialNumberTest2")
                .type(PartType.Basic)
                .cyclesSinceNew("CycleSinceNewTest2")
                .timeSinceNew("TimeSinceNewTest2")
                .build();

        when(basicPartRepository.findById(1)).thenReturn(basicPart1);
        when(basicPartMapper.toDto(basicPart1)).thenReturn(basicPartDto1);
        when(basicPartRepository.findById(2)).thenReturn(basicPart2);
        when(basicPartMapper.toDto(basicPart2)).thenReturn(basicPartDto2);

        BasicPartDto foundBasicPartDto1 = basicPartServiceImpl.getById(1);
        BasicPartDto foundBasicPartDto2 = basicPartServiceImpl.getById(2);


        assertThat(foundBasicPartDto1).isEqualTo(basicPartDto1);
        assertThat(foundBasicPartDto2).isEqualTo(basicPartDto2);
    }




}
