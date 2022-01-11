package fr.bred.example.interview.model;

import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import static org.assertj.core.api.Assertions.assertThat;

class CityOffsetPageTest {

    @Test
    void should_return_nominal_pageable() {
        CityOffsetPage cityOffsetPage = new CityOffsetPage(1, 0, SortBy.name, SortDirection.ASC);
        Pageable pageable = cityOffsetPage.getPageable();
        assertThat(pageable.getOffset()).isEqualTo(0);
        assertThat(pageable.getPageSize()).isEqualTo(1);
        assertThat(pageable.getSort()).isEqualTo(Sort.by(SortDirection.ASC.getDirection(), SortBy.name.name()));
    }

    @Test
    void should_return_pageable_when_only_size() {
        CityOffsetPage cityOffsetPage = new CityOffsetPage(5, null, null, null);
        Pageable pageable = cityOffsetPage.getPageable();
        assertThat(pageable.getOffset()).isEqualTo(0);
        assertThat(pageable.getPageSize()).isEqualTo(5);
        assertThat(pageable.getSort()).isEqualTo(Sort.unsorted());
    }

    @Test
    void should_return_pageable_when_only_offset() {
        CityOffsetPage cityOffsetPage = new CityOffsetPage(1, 9, null, null);
        Pageable pageable = cityOffsetPage.getPageable();
        assertThat(pageable.getOffset()).isEqualTo(9);
        assertThat(pageable.getPageSize()).isEqualTo(1);
        assertThat(pageable.getSort()).isEqualTo(Sort.unsorted());
    }

    @Test
    void should_return_pageable_when_only_sortBy() {
        CityOffsetPage cityOffsetPage = new CityOffsetPage(null, null, SortBy.zipCode, null);
        Pageable pageable = cityOffsetPage.getPageable();
        assertThat(pageable.getOffset()).isEqualTo(0);
        assertThat(pageable.getPageSize()).isEqualTo(Integer.MAX_VALUE);
        assertThat(pageable.getSort()).isEqualTo(Sort.by(SortDirection.DESC.getDirection(), SortBy.zipCode.name()));
    }

    @Test
    void should_return_pageable_when_only_direction() {
        CityOffsetPage cityOffsetPage = new CityOffsetPage(null, null, null, SortDirection.DESC);
        Pageable pageable = cityOffsetPage.getPageable();
        assertThat(pageable.getOffset()).isEqualTo(0);
        assertThat(pageable.getPageSize()).isEqualTo(Integer.MAX_VALUE);
        assertThat(pageable.getSort()).isEqualTo(Sort.by(SortDirection.DESC.getDirection(), "id"));
    }

    @Test
    void should_return_default_pageable_when_city_offset_page_is_null() {
        CityOffsetPage cityOffsetPage = new CityOffsetPage(null, null, null, null);
        Pageable pageable = cityOffsetPage.getPageable();
        assertThat(pageable.getOffset()).isEqualTo(0);
        assertThat(pageable.getPageSize()).isEqualTo(Integer.MAX_VALUE);
        assertThat(pageable.getSort()).isEqualTo(Sort.unsorted());
    }
}