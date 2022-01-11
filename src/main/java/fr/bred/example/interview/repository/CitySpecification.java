package fr.bred.example.interview.repository;

import fr.bred.example.interview.model.City;
import fr.bred.example.interview.model.CityCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Custom specification for city entity.
 */
@RequiredArgsConstructor
public class CitySpecification implements Specification<City> {
    private final CityCriteria criteria;

    @Override
    public Predicate toPredicate(Root<City> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();
        namePattern(root, builder, criteria).ifPresent(predicates::add);
        zipCodePattern(root, builder, criteria).ifPresent(predicates::add);
        query.distinct(true);
        return builder.and(predicates.toArray(new Predicate[0]));
    }

    private Optional<Predicate> namePattern(Root<City> root, CriteriaBuilder builder, CityCriteria criteria) {
        if (Objects.isNull(criteria.getNamePattern())) {
            return Optional.empty();
        }
        return Optional.of(builder.like(builder.upper(root.get("name")), criteria.getNamePattern()));
    }

    private Optional<Predicate> zipCodePattern(Root<City> root, CriteriaBuilder builder, CityCriteria criteria) {
        if (Objects.isNull(criteria.getZipCodePattern())) {
            return Optional.empty();
        }
        return Optional.of(builder.like(builder.upper(root.get("zipCode").as(String.class)), criteria.getZipCodePattern()));
    }

}

