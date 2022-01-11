package fr.bred.example.interview.service;

import fr.bred.example.interview.dto.CityDto;
import fr.bred.example.interview.mapper.CityMapper;
import fr.bred.example.interview.model.City;
import fr.bred.example.interview.model.CityCriteria;
import fr.bred.example.interview.model.CityOffsetPage;
import fr.bred.example.interview.model.Coordinate;
import fr.bred.example.interview.repository.CityRepository;
import fr.bred.example.interview.repository.CitySpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

/**
 * The service to manage the city.
 */
@Service
@RequiredArgsConstructor
public class CityService {

    private final CityRepository cityRepository;

    /**
     * Retrieves the cities by criteria pattern or offset page.
     *
     * @param criteria       city criteria
     * @param cityOffsetPage offset page
     * @return list of city
     */
    public List<CityDto> getCities(CityCriteria criteria, CityOffsetPage cityOffsetPage) {
        requireNonNull(criteria, "criteria must not be null");
        requireNonNull(cityOffsetPage, "cityOffsetPage must not be null");
        return cityRepository.findAll(new CitySpecification(criteria), cityOffsetPage.getPageable())
                .stream()
                .map(CityMapper.CITY_MAPPER::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Find the city nearest the coordinate given.
     *
     * @param coordinate coordinate
     * @return city nearest
     */
    public CityDto getNearestCity(Coordinate coordinate) {
        requireNonNull(coordinate, "coordinate must not be null");
        List<City> cities = cityRepository.findAll();
        return CityMapper.CITY_MAPPER.toDto(City.findNearest(cities, coordinate));
    }

    /**
     * Create a list of cities.
     *
     * @param cities cities to save
     */
    public void createCities(List<City> cities) {
        cityRepository.saveAll(cities);
    }
}
