package com.figures.figures.specifiactionBuilder;

import com.figures.figures.models.Figure;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class FigureSpecificationBuilder {
    public Specification<Figure> buildSpecification(Map<String, String> params) {
        return (root, query, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();

            for (Map.Entry<String, String> entry : params.entrySet()) {

                if ("type".equals(entry.getKey())) {
                    predicate = criteriaBuilder.and(
                            predicate,
                            criteriaBuilder.equal(
                                    root.get("type"),
                                    entry.getValue()
                            )
                    );
                } else {
                    Expression<Double> parameterValue = criteriaBuilder.function(
                            "JSON_EXTRACT",
                            Double.class,
                            root.get("parameters"),
                            criteriaBuilder.literal("$." + entry.getKey())
                    );

                    predicate = criteriaBuilder.and(
                            predicate,
                            criteriaBuilder.equal(
                                    parameterValue,
                                    Double.valueOf(entry.getValue())
                            )
                    );
                }
            }

            return predicate;
        };
    }

    private boolean isValidParameter(Map.Entry<String, String> entry) {
        return entry.getKey().equals("type") || !entry.getValue().isEmpty();
    }

}
