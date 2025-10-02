package com.example.document_review.service;

import com.example.document_review.dto.FanBladeDto;
import com.example.document_review.entity.enums.PartType;
import com.example.document_review.entity.FanBlade;
import com.example.document_review.mapper.impl.FanBladeMapper;
import com.example.document_review.repository.impl.FanBladeRepository;
import com.example.document_review.service.impl.FanBladeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
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

    private FanBlade fanBlade;
    private FanBladeDto fanBladeDto;
    private FanBlade fanBlade2;
    private FanBladeDto fanBladeDto2;


    @BeforeEach
    public void setUp() {
        fanBlade = FanBlade.builder()
                .partNumber("PartNumberTest")
                .name("NameTest")
                .description("DescriptionTest")
                .serialNumber("SerialNumberTest")
                .type(PartType.FAN_BLADE)
                .cyclesSinceNew("CycleSinceNewTest")
                .timeSinceNew("TimeSinceNewTest")
                .momentWeight("MomentWeightTest")
                .build();
        fanBladeDto = FanBladeDto.builder()
                .partNumber("PartNumberTest")
                .name("NameTest")
                .description("DescriptionTest")
                .serialNumber("SerialNumberTest")
                .type(PartType.FAN_BLADE)
                .cyclesSinceNew("CycleSinceNewTest")
                .timeSinceNew("TimeSinceNewTest")
                .momentWeight("MomentWeightTest")
                .build();
        fanBlade2 = FanBlade.builder()
                .partNumber("PartNumberTest2")
                .name("NameTest2")
                .description("DescriptionTest2")
                .serialNumber("SerialNumberTest2")
                .type(PartType.FAN_BLADE)
                .cyclesSinceNew("CycleSinceNewTest2")
                .timeSinceNew("TimeSinceNewTest2")
                .momentWeight("MomentWeightTest2")
                .build();
        fanBladeDto2 = FanBladeDto.builder()
                .partNumber("PartNumberTest2")
                .name("NameTest2")
                .description("DescriptionTest2")
                .serialNumber("SerialNumberTest2")
                .type(PartType.FAN_BLADE)
                .cyclesSinceNew("CycleSinceNewTest2")
                .timeSinceNew("TimeSinceNewTest2")
                .momentWeight("MomentWeightTest2")
                .build();
    }


    @InjectMocks
    FanBladeServiceImpl fanBladeServiceImpl;

    @Test
    public void fanBladeServiceSave() {


        when(fanBladeMapper.toEntity(fanBladeDto)).thenReturn(fanBlade);

        fanBladeServiceImpl.save(fanBladeDto);

        verify(fanBladeMapper, times(1)).toEntity(fanBladeDto);

        verify(fanBladeMapper, times(1)).toEntity(fanBladeDto);
        verify(fanBladeRepository, times(1)).save(fanBlade);
    }

    @Test
    public void fanBladeServiceDelete() throws Exception {

        fanBladeServiceImpl.delete(fanBlade.getPartId());

        verify(fanBladeRepository).delete(fanBlade.getPartId());
    }

    @Test
    public void fanBladeServiceGetAllFanBladeDtos() throws Exception {


        when(fanBladeRepository.findAll()).thenReturn(List.of(fanBlade,fanBlade2));
        when(fanBladeMapper.toDto(fanBlade)).thenReturn(fanBladeDto);
        when(fanBladeMapper.toDto(fanBlade2)).thenReturn(fanBladeDto2);

        List<FanBladeDto> fanBladeDtos = fanBladeServiceImpl.getAll();

        assertThat(fanBladeDtos).hasSize(2);


    }
    @Test
    public void fanBladeServiceGetByIdFanBladeDto() {

        when(fanBladeRepository.findById(1)).thenReturn(fanBlade);
        when(fanBladeMapper.toDto(fanBlade)).thenReturn(fanBladeDto);
        when(fanBladeRepository.findById(2)).thenReturn(fanBlade2);
        when(fanBladeMapper.toDto(fanBlade2)).thenReturn(fanBladeDto2);

        FanBladeDto foundFanBladeDto1 = fanBladeServiceImpl.getById(1);
        FanBladeDto foundFanBladeDto2 = fanBladeServiceImpl.getById(2);


        assertThat(foundFanBladeDto1).isEqualTo(fanBladeDto);
        assertThat(foundFanBladeDto2).isEqualTo(fanBladeDto2);
    }


}













