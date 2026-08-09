package com.figures.figures.strategies;

import com.figures.figures.exceptions.InvalidFigureParametersException;
import com.figures.figures.models.CreateFigureCommand;
import com.figures.figures.models.Figure;
import org.springframework.stereotype.Component;

@Component("CIRCLE")
public class CreateCircleStrategy implements CreateFigureStrategy {

    @Override
    public Figure create(CreateFigureCommand command) {
        if (!(command.getParameters().containsKey("radius"))) {
            throw new InvalidFigureParametersException("Wrong parameters!");
        }
        return Figure.builder()
                .type(command.getType())
                .parameters(command.getParameters())
                .build();
    }
}
