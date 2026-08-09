package com.figures.figures.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Map;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Figure {

    @Id
    @GeneratedValue
    private Long id;

    private String type;

    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Double> parameters;
}
