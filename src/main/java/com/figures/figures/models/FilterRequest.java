package com.figures.figures.models;

import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class FilterRequest {
    private long id;
    private String type;
    private String parameter;
}
