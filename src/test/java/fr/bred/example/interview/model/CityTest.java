package fr.bred.example.interview.model;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CityTest {

    @Test
    void should_return_nominal_city() {
        Coordinate coordinate = new Coordinate(5.0, 3.0);
        City city = new City(1L, "Paris", 75000, coordinate);

        assertThat(city.getId()).isEqualTo(1L);
        assertThat(city.getName()).isEqualTo("Paris");
        assertThat(city.getZipCode()).isEqualTo(75000);
        assertThat(city.getCoordinates().getX()).isEqualTo(5.0);
    }

    @Test
    void should_return_city_with_null_coordinates() {
        City city = new City(1L, "Paris", 75000, null);

        assertThat(city.getId()).isEqualTo(1L);
        assertThat(city.getName()).isEqualTo("Paris");
        assertThat(city.getZipCode()).isEqualTo(75000);
        assertThat(city.getCoordinates()).isNull();
    }

    @Test
    void should_return_nullPointerException_when_name_is_null() {
        Exception exception = assertThrows(NullPointerException.class, () ->
                new City(1L, null, 75000, new Coordinate(5.0, 3.0)));
        assertThat(exception.getMessage()).isEqualTo("name must not be null");
    }

    @Test
    void should_return_nullPointerException_when_zipCode_is_null() {
        Exception exception = assertThrows(NullPointerException.class, () ->
                new City(1L, "Paris", null, new Coordinate(5.0, 3.0)));
        assertThat(exception.getMessage()).isEqualTo("zipCode must not be null");
    }

    @Test
    void should_return_nearest_city() {
        Coordinate coordinate1 = new Coordinate(50.00, 30.00);
        City city1 = new City(1L, "Paris", 75000, coordinate1);
        Coordinate coordinate2 = new Coordinate(100.00, 10.00);
        City city2 = new City(2L, "Lyon", 40000, coordinate2);
        Coordinate coordinate3 = new Coordinate(10.00, 5.00);
        City city3 = new City(3L, "Marseille", 13000, coordinate3);
        List<City> cities = List.of(city1, city2, city3);

        assertThat(City.findNearest(cities, coordinate1).getName()).isEqualTo("Paris");
        assertThat(City.findNearest(cities, coordinate2).getName()).isEqualTo("Lyon");
        assertThat(City.findNearest(cities, coordinate3).getName()).isEqualTo("Marseille");
    }

    @Test
    void should_return_null_nearest_city_when_cities_is_empty() {
        List<City> cities = Collections.emptyList();
        Coordinate coordinate1 = new Coordinate(50.00, 30.00);

        assertThat(City.findNearest(cities, coordinate1)).isNull();
    }

    @Test
    void should_return_null_nearest_city_when_coordinates_is_null() {
        Coordinate coordinate1 = new Coordinate(50.00, 30.00);
        City city1 = new City(1L, "Paris", 75000, null);
        List<City> cities = Collections.singletonList(city1);

        assertThat(City.findNearest(cities, coordinate1)).isNull();
    }

}