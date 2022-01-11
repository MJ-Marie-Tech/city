package fr.bred.example.interview.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CoordinateTest {

    @Test
    void should_return_nominal_coordinate() {
        Coordinate coordinate = new Coordinate(16.00, 16.00);
        assertThat(coordinate.getX()).isEqualTo(16.00);
        assertThat(coordinate.getY()).isEqualTo(16.00);
    }

    @Test
    void should_return_distance_between_two_coordinates() {
        Coordinate coordinate = new Coordinate(5.00, 3.00);
        Coordinate coordinate2 = new Coordinate(2.00, 3.00);
        assertThat(coordinate.distance(coordinate2)).isEqualTo(3.0);
    }

    @Test
    void should_return_nullPointerException_when_y_null() {
        Exception exception = assertThrows(NullPointerException.class, () -> new Coordinate(5.00, null));
        assertThat(exception.getMessage()).isEqualTo("y must not be null");
    }

    @Test
    void should_return_nullPointerException_when_x_null() {
        Exception exception = assertThrows(NullPointerException.class, () -> new Coordinate(null, 3.00));
        assertThat(exception.getMessage()).isEqualTo("x must not be null");
    }

}