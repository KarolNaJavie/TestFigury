package com.figures.figures.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateFigureCommand {
    private String type;
    private Map<String, Double> parameters;
}
