package com.figures.figures.models;

import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class FilterRequest {
    private String type;
    private String parameter;
    private Double value;
}
