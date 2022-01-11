package fr.bred.example.interview.mapper;


import fr.bred.example.interview.dto.CityDto;
import fr.bred.example.interview.model.City;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(unmappedTargetPolicy = IGNORE)
public interface CityMapper {

    CityMapper CITY_MAPPER = Mappers.getMapper(CityMapper.class);

    City toEntity(CityDto dto);

    CityDto toDto(City entity);
}
