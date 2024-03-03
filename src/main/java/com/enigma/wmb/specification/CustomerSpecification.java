package com.enigma.wmb.specification;

import com.enigma.wmb.dto.request.customer.NewCustomerRequest;
import com.enigma.wmb.dto.request.customer.SearchCustomerRequest;
import com.enigma.wmb.entity.Customer;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class CustomerSpecification {
    public static Specification<Customer> getSpecification(SearchCustomerRequest request){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> filterCustomer = new ArrayList<>();
            if (request.getName() != null){
                Predicate namePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + request.getName().toLowerCase() + "%");
                filterCustomer.add(namePredicate);
            }

            if (request.getPhone() != null){
                Predicate phonePredicate = criteriaBuilder.equal(root.get("phone"), request.getPhone());
                filterCustomer.add(phonePredicate);

            }

            if (request.getMember() != null){
                Predicate memberPredicate = criteriaBuilder.equal(root.get("member"), request.getMember());
                filterCustomer.add(memberPredicate);
            }

             return query.where(
                     filterCustomer.toArray(new Predicate[]{})
             ).getRestriction();
        };
    }
}