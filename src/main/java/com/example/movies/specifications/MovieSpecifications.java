package com.example.movies.specifications;

import com.example.movies.models.Movie;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;




public class MovieSpecifications {
    public static Specification<Movie> nameContains(String substring) {
        return (Root<Movie> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (substring == null){
                return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
            }
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%".concat(substring.toLowerCase().concat("%")));
        };
    }
}
