package com.enigma.wmb.specification;

import com.enigma.wmb.dto.request.bill.SearchBillRequest;
import com.enigma.wmb.entity.Bill;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class BillSpecification {
    public static Specification<Bill> getSpecification(SearchBillRequest request) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (request.getId() != null) {
                predicates.add(criteriaBuilder.equal
                        (root.get("id"), request.getId()
                        )
                );
            }

            if (request.getCustomer() != null) {
                predicates.add(criteriaBuilder.equal
                        (root.get("customerId"), request.getCustomer()
                        )
                );
            }

            if (request.getTable() != null) {
                predicates.add(criteriaBuilder.equal
                        (root.get("tableId"), request.getTable()
                        )
                );
            }

            if (request.getTransDate() != null) {
                predicates.add(criteriaBuilder.equal
                        (root.get("transDate"), request.getTransDate()
                        )
                );
            }
            return query.where(predicates.toArray(new Predicate[]{})).getRestriction();
        };
    }
}
