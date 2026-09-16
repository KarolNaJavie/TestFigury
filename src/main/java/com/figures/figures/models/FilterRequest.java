package com.figures.figures.models;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Builder
@Data
public class FilterRequest {
  private Map<String, String> params;
}
