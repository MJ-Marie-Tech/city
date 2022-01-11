package fr.bred.example.interview.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

@Entity
@Table(name = "cities")
@Getter
@Setter
@NoArgsConstructor
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private Integer zipCode;

    @Embedded
    private Coordinate coordinates;

    public City(Long id, String name, Integer zipCode, Coordinate coordinates) {
        this.id = id;
        this.name = requireNonNull(name, "name must not be null");
        this.zipCode = requireNonNull(zipCode, "zipCode must not be null");
        this.coordinates = coordinates;
    }

    /**
     * Retrieves the nearest city according to the given coordinate.
     *
     * @param cities     list of city
     * @param coordinate given coordinate
     * @return city found
     */
    public static City findNearest(List<City> cities, Coordinate coordinate) {
        Double minDistance = Double.MAX_VALUE;
        City city = null;
        List<City> cityList = cities.stream()
                .filter(c -> Objects.nonNull(c.getCoordinates()))
                .collect(Collectors.toList());
        for (City currentCity : cityList) {
            Double distance = currentCity.coordinates.distance(coordinate);
            if (distance < minDistance) {
                minDistance = distance;
                city = currentCity;
            }
        }
        return city;
    }
}
