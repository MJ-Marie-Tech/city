package fr.bred.example.interview.controller;

import fr.bred.example.interview.dto.CityDto;
import fr.bred.example.interview.model.*;
import fr.bred.example.interview.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.lang.Double.parseDouble;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/cities")
public class CityController {

    private final CityService cityService;

    @GetMapping()
    public List<CityDto> getCities(@RequestParam(required = false) Integer size,
                                   @RequestParam(required = false, defaultValue = "0") Integer offset,
                                   @RequestParam(required = false) SortBy sortBy,
                                   @RequestParam(required = false, defaultValue = "DESC") SortDirection sortDirection,
                                   @RequestParam(required = false, defaultValue = "*") String namePattern,
                                   @RequestParam(required = false, defaultValue = "*") String zipCodePattern
    ) {
        CityOffsetPage cityOffsetPage = new CityOffsetPage(size, offset, sortBy, sortDirection);
        CityCriteria criteria = new CityCriteria(namePattern, zipCodePattern);
        return cityService.getCities(criteria, cityOffsetPage);
    }

    @GetMapping("/nearest")
    public CityDto getNearestCity(@RequestParam() String x,
                                  @RequestParam() String y) {
        Coordinate coordinate = new Coordinate(parseDouble(x), parseDouble(y));
        return cityService.getNearestCity(coordinate);
    }
}
