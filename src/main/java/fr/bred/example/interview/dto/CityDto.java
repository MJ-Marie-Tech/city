package fr.bred.example.interview.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CityDto {

    private String name;

    private Integer zipCode;

    private CoordinateDto coordinates;
}
