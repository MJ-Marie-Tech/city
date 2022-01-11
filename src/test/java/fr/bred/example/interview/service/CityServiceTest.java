package fr.bred.example.interview.service;

import fr.bred.example.interview.dto.CityDto;
import fr.bred.example.interview.model.*;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@RunWith(SpringRunner.class)
@SpringBootTest
class CityServiceTest {

    @Autowired
    CityService cityService;

    @Test
    public void should_return_list_of_cities_when_search_nominal() {
        CityCriteria criteria = new CityCriteria("A*", "75*");
        CityOffsetPage offsetPage = new CityOffsetPage(10, 0, SortBy.name, SortDirection.ASC);
        List<CityDto> results = cityService.getCities(criteria, offsetPage);

        assertThat(results.size()).isEqualTo(2);
        assertThat(results.get(0).getName()).isEqualTo("AIZAC");
        assertThat(results.get(1).getZipCode()).isEqualTo(7530);
    }

    @Test
    public void should_return_list_of_cities_when_search_all_cities() {
        CityCriteria criteria = new CityCriteria(null, null);
        CityOffsetPage offsetPage = new CityOffsetPage(null, null, null, null);
        List<CityDto> results = cityService.getCities(criteria, offsetPage);

        assertThat(results.size()).isEqualTo(36344);
    }

    @Test
    public void should_throw_exception_when_criteria_and_offsetpage_are_nulls() {
        Exception exception = assertThrows(NullPointerException.class, () -> {
            cityService.getCities(null, null);
        });
        assertThat(exception.getMessage()).isEqualTo("criteria must not be null");
    }

    @Test
    public void should_return_city_nearest() {
        Coordinate coordinate = new Coordinate(2.0, 9.0);
        CityDto result = cityService.getNearestCity(coordinate);

        assertThat(result.getName()).isEqualTo("MTSAMBORO");
    }

    @Test
    public void should_throw_exception_when_coordinate_is_null() {
        Exception exception = assertThrows(NullPointerException.class, () -> {
            cityService.getNearestCity(null);
        });
        assertThat(exception.getMessage()).isEqualTo("coordinate must not be null");
    }

}