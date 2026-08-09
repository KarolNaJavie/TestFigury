package com.figures.figures.strategies;

import com.figures.figures.exceptions.InvalidFigureParametersException;
import com.figures.figures.models.CreateFigureCommand;
import com.figures.figures.models.Figure;
import org.springframework.stereotype.Component;

@Component("TRIANGLE")
public class CreateTriangleStrategy implements CreateFigureStrategy {
    @Override
    public Figure create(CreateFigureCommand command) {
        if (!(command.getParameters().containsKey("height") && command.getParameters().containsKey("base"))) {
            throw new InvalidFigureParametersException("Wrong parameters!");
        }
        return Figure.builder()
                .type(command.getType())
                .parameters(command.getParameters())
                .build();
    }
}
