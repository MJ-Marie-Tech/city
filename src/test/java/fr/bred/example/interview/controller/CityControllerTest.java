package fr.bred.example.interview.controller;

import fr.bred.example.interview.dto.CityDto;
import fr.bred.example.interview.dto.CoordinateDto;
import fr.bred.example.interview.service.CityService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(CityController.class)
@MockBean(CityService.class)
class CityControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private CityService cityService;

    @Test
    public void should_return_city_when_getCities_has_cities()
            throws Exception {

        when(cityService.getCities(any(), any()))
                .thenReturn(List.of(new CityDto("Paris", 7500, new CoordinateDto("2.34", "2.35"))));

        this.mvc.perform(get("/api/cities")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name", is("Paris")));
    }

    @Test
    public void should_return_bad_request_when_parameters_are_wrong()
            throws Exception {

        when(cityService.getCities(any(), any())).thenReturn(Collections.emptyList());

        this.mvc.perform(get("/api/cities?size=e")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void should_return_empty_list_when_getCities_is_empty()
            throws Exception {

        when(cityService.getCities(any(), any())).thenReturn(Collections.emptyList());

        this.mvc.perform(get("/api/cities")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", is(0)));
    }

    @Test
    public void should_return_city_when_getNearest_city()
            throws Exception {

        when(cityService.getNearestCity(any()))
                .thenReturn(new CityDto("Paris", 7500, new CoordinateDto("2.34", "2.35")));

        this.mvc.perform(get("/api/cities/nearest?x=0&y=0")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is("Paris")));
    }

    @Test
    public void should_return_bad_request_when_coordinates_are_not_given()
            throws Exception {

        when(cityService.getNearestCity(any()))
                .thenReturn(new CityDto("Paris", 7500, new CoordinateDto("2.34", "2.35")));

        this.mvc.perform(get("/api/cities/nearest")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

}