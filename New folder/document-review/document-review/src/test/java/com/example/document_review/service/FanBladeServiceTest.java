package com.example.document_review.service;

import com.example.document_review.dto.FanBladeDto;
import com.example.document_review.entity.Enums.PartType;
import com.example.document_review.entity.FanBlade;
import com.example.document_review.mapper.impl.FanBladeMapper;
import com.example.document_review.repository.impl.FanBladeRepository;
import com.example.document_review.service.impl.FanBladeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FanBladeServiceTest {

    @Mock
    FanBladeRepository fanBladeRepository;

    @Mock
    FanBladeMapper fanBladeMapper;

    @InjectMocks
    FanBladeServiceImpl fanBladeServiceImpl;

    @Test
    public void FanBladeService_Save() {
        FanBlade fanBlade = FanBlade.builder()
                .partNumber("PartNumberTest")
                .name("NameTest")
                .description("DescriptionTest")
                .serialNumber("SerialNumberTest")
                .type(PartType.Basic)
                .cyclesSinceNew("CycleSinceNewTest")
                .timeSinceNew("TimeSinceNewTest")
                .momentWeight("MomentWeightTest")
                .build();
        FanBladeDto fanBladeDto = FanBladeDto.builder()
                .partNumber("PartNumberTest")
                .name("NameTest")
                .description("DescriptionTest")
                .serialNumber("SerialNumberTest")
                .type(PartType.Basic)
                .cyclesSinceNew("CycleSinceNewTest")
                .timeSinceNew("TimeSinceNewTest")
                .momentWeight("MomentWeightTest")
                .build();

        when(fanBladeMapper.toEntity(fanBladeDto)).thenReturn(fanBlade);

        fanBladeServiceImpl.save(fanBladeDto);

        verify(fanBladeMapper, times(1)).toEntity(fanBladeDto);

        verify(fanBladeMapper, times(1)).toEntity(fanBladeDto);
        verify(fanBladeRepository, times(1)).save(fanBlade);
    }

    @Test
    public void FanBladeService_Delete() throws Exception {
        FanBlade fanBlade = FanBlade.builder()
                .partNumber("PartNumberTest")
                .name("NameTest")
                .description("DescriptionTest")
                .serialNumber("SerialNumberTest")
                .type(PartType.Basic)
                .cyclesSinceNew("CycleSinceNewTest")
                .timeSinceNew("TimeSinceNewTest")
                .momentWeight("MomentWeightTest")
                .build();

        fanBladeServiceImpl.delete(fanBlade.getPartId());

        verify(fanBladeRepository).delete(fanBlade.getPartId());
    }

    @Test
    public void FanBladeService_GetAll_FanBladeDtos() throws Exception {
        FanBlade fanBlade1 = FanBlade.builder()
                .partNumber("PartNumberTest1")
                .name("NameTest1")
                .description("DescriptionTest1")
                .serialNumber("SerialNumberTest1")
                .type(PartType.Basic)
                .cyclesSinceNew("CycleSinceNewTest1")
                .timeSinceNew("TimeSinceNewTest1")
                .momentWeight("MomentWeightTest1")
                .build();
        FanBlade fanBlade2 = FanBlade.builder()
                .partNumber("PartNumberTest2")
                .name("NameTest2")
                .description("DescriptionTest2")
                .serialNumber("SerialNumberTest2")
                .type(PartType.Basic)
                .cyclesSinceNew("CycleSinceNewTest2")
                .timeSinceNew("TimeSinceNewTest2")
                .momentWeight("MomentWeightTest2")
                .build();
        FanBladeDto fanBladeDto1 = FanBladeDto.builder()
                .partNumber("PartNumberTest1")
                .name("NameTest1")
                .description("DescriptionTest1")
                .serialNumber("SerialNumberTest1")
                .type(PartType.Basic)
                .cyclesSinceNew("CycleSinceNewTest1")
                .timeSinceNew("TimeSinceNewTest1")
                .momentWeight("MomentWeightTest1")
                .build();
        FanBladeDto fanBladeDto2 = FanBladeDto.builder()
                .partNumber("PartNumberTest2")
                .name("NameTest2")
                .description("DescriptionTest2")
                .serialNumber("SerialNumberTest2")
                .type(PartType.Basic)
                .cyclesSinceNew("CycleSinceNewTest2")
                .timeSinceNew("TimeSinceNewTest2")
                .momentWeight("MomentWeightTest2")
                .build();

        when(fanBladeRepository.findAll()).thenReturn(List.of(fanBlade1,fanBlade2));
        when(fanBladeMapper.toDto(fanBlade1)).thenReturn(fanBladeDto1);
        when(fanBladeMapper.toDto(fanBlade2)).thenReturn(fanBladeDto2);

        List<FanBladeDto> fanBladeDtos = fanBladeServiceImpl.getAll();

        assertThat(fanBladeDtos).hasSize(2);


    }
    @Test
    public void FanBladeService_GetById_FanBladeDto() {
        FanBlade fanBlade1 = FanBlade.builder()
                .partNumber("PartNumberTest1")
                .name("NameTest1")
                .description("DescriptionTest1")
                .serialNumber("SerialNumberTest1")
                .type(PartType.Basic)
                .cyclesSinceNew("CycleSinceNewTest1")
                .timeSinceNew("TimeSinceNewTest1")
                .momentWeight("MomentWeightTest1")
                .build();
        FanBlade fanBlade2 = FanBlade.builder()
                .partNumber("PartNumberTest2")
                .name("NameTest2")
                .description("DescriptionTest2")
                .serialNumber("SerialNumberTest2")
                .type(PartType.Basic)
                .cyclesSinceNew("CycleSinceNewTest2")
                .timeSinceNew("TimeSinceNewTest2")
                .momentWeight("MomentWeightTest2")
                .build();
        FanBladeDto fanBladeDto1 = FanBladeDto.builder()
                .partNumber("PartNumberTest1")
                .name("NameTest1")
                .description("DescriptionTest1")
                .serialNumber("SerialNumberTest1")
                .type(PartType.Basic)
                .cyclesSinceNew("CycleSinceNewTest1")
                .timeSinceNew("TimeSinceNewTest1")
                .momentWeight("MomentWeightTest1")
                .build();
        FanBladeDto fanBladeDto2 = FanBladeDto.builder()
                .partNumber("PartNumberTest2")
                .name("NameTest2")
                .description("DescriptionTest2")
                .serialNumber("SerialNumberTest2")
                .type(PartType.Basic)
                .cyclesSinceNew("CycleSinceNewTest2")
                .timeSinceNew("TimeSinceNewTest2")
                .momentWeight("MomentWeightTest2")
                .build();

        when(fanBladeRepository.findById(1)).thenReturn(fanBlade1);
        when(fanBladeMapper.toDto(fanBlade1)).thenReturn(fanBladeDto1);
        when(fanBladeRepository.findById(2)).thenReturn(fanBlade2);
        when(fanBladeMapper.toDto(fanBlade2)).thenReturn(fanBladeDto2);

        FanBladeDto foundFanBladeDto1 = fanBladeServiceImpl.getById(1);
        FanBladeDto foundFanBladeDto2 = fanBladeServiceImpl.getById(2);


        assertThat(foundFanBladeDto1).isEqualTo(fanBladeDto1);
        assertThat(foundFanBladeDto2).isEqualTo(fanBladeDto2);
    }


}













