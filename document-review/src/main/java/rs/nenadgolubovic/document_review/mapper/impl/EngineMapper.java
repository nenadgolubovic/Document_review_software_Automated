package mapper.impl;

import dto.EngineDto;
import mapper.Mapper;
import model.Engine;

public class EngineMapper implements Mapper<EngineDto, Engine> {

    @Override
    public Engine toEntity(EngineDto engineDto) {
        return null;
    }

    @Override
    public EngineDto toDto(Engine engine) {
        return null;
    }
}
