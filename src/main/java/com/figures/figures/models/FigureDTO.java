package com.figures.figures.models;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@Builder
public class FigureDTO {
    private Long id;
    private String type;
    private Map<String, Double> parameters;

    public static FigureDTO fromEntity(Figure figure) {
        return FigureDTO.builder()
                .id(figure.getId())
                .type(figure.getType())
                .parameters(figure.getParameters())
                .build();
    }
}
