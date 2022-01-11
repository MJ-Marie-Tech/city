package fr.bred.example.interview.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import static java.util.Objects.nonNull;

/**
 * City offset page represents the pageable request.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CityOffsetPage {
    private static final String DEFAULT_SORT_BY = "id";
    private static final Integer DEFAULT_SIZE = Integer.MAX_VALUE;
    private static final SortDirection DEFAULT_DIRECTION = SortDirection.DESC;
    private static final Integer DEFAULT_OFFSET = 0;

    private Integer size;
    private Integer offset;
    private SortBy sortBy;
    private SortDirection sortDirection;

    /**
     * Returns pageable according to sort, size and offset.
     *
     * @return pageable
     */
    public Pageable getPageable() {
        Sort sort = getSort();
        int sizeFinal = DEFAULT_SIZE;
        int offsetFinal = DEFAULT_OFFSET;
        if (nonNull(size)) {
            sizeFinal = size;
        }
        if (nonNull(offset)) {
            offsetFinal = offset;
        }
        return PageRequest.of(offsetFinal, sizeFinal, sort);
    }

    /**
     * Returns sort according to sort by and direction.
     *
     * @return sort
     */
    private Sort getSort() {
        if (nonNull(sortBy) && nonNull(sortDirection)) {
            return Sort.by(sortDirection.getDirection(), sortBy.name());
        }
        if (nonNull(sortBy)) {
            return Sort.by(DEFAULT_DIRECTION.getDirection(), sortBy.name());
        }
        if (nonNull(sortDirection)) {
            return Sort.by(sortDirection.getDirection(), DEFAULT_SORT_BY);
        }
        return Sort.unsorted();
    }

}