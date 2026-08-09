package com.figures.figures.strategies;

import com.figures.figures.models.CreateFigureCommand;
import com.figures.figures.models.Figure;

public interface CreateFigureStrategy {
    Figure create(CreateFigureCommand command);
}
