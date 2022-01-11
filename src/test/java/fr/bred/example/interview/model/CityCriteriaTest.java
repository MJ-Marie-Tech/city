package fr.bred.example.interview.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CityCriteriaTest {

    @Test
    void should_return_nominal_city_criteria() {
        CityCriteria cityCriteria = new CityCriteria("*ab*", "75*");
        assertThat(cityCriteria.getNamePattern()).isEqualTo("%AB%");
        assertThat(cityCriteria.getZipCodePattern()).isEqualTo("75%");
    }

    @Test
    void should_return_criteria_with_exact_city_code() {
        CityCriteria cityCriteria = new CityCriteria("*", "75000");
        assertThat(cityCriteria.getNamePattern()).isEqualTo("%");
        assertThat(cityCriteria.getZipCodePattern()).isEqualTo("75000");
    }

    @Test
    void should_return_criteria_with_exact_city_name() {
        CityCriteria cityCriteria = new CityCriteria("Paris", "*");
        assertThat(cityCriteria.getNamePattern()).isEqualTo("PARIS");
        assertThat(cityCriteria.getZipCodePattern()).isEqualTo("%");
    }

    @Test
    void should_return_null_when_pattern_is_null() {
        CityCriteria cityCriteria = new CityCriteria(null, null);
        assertThat(cityCriteria.getNamePattern()).isEqualTo(null);
        assertThat(cityCriteria.getZipCodePattern()).isEqualTo(null);
    }

}