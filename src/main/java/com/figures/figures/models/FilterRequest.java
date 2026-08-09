package com.figures.figures.models;

import lombok.Data;

import java.util.List;

@Data
public class FilterRequest {
    private long id;
    private String type;
    private String parameter;
}
