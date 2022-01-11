package fr.bred.example.interview.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;

import static java.util.Objects.requireNonNull;

@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class Coordinate {

    private Double x;

    private Double y;

    public Coordinate(Double x, Double y) {
        this.x = requireNonNull(x, "x must not be null");
        this.y = requireNonNull(y, "y must not be null");
    }

    /**
     * Compute distance between two coordinates.
     *
     * @param coordinate other coordinate
     * @return distance
     */
    public Double distance(Coordinate coordinate) {
        return Math.sqrt(Math.pow(x - coordinate.x, 2)
                + Math.pow(y - coordinate.y, 2));
    }
}
