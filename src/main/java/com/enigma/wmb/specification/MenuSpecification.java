package com.enigma.wmb.specification;

import com.enigma.wmb.dto.request.menu.SearchMenuRequest;
import com.enigma.wmb.entity.Menu;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class MenuSpecification {
    public static Specification<Menu> getSpecification(SearchMenuRequest request) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (request.getName() != null) {
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(
                                        root.get("name")), "%"+request.getName()+"%"
                        ));
            }

            if (request.getPrice() != null) {
                predicates.add(
                        criteriaBuilder.equal(
                                root.get("price"), request.getPrice())
                );
            }
            return query.where(predicates.toArray(new Predicate[]{})).getRestriction();
        };
    }
}
