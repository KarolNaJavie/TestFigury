package com.figures.figures.strategies;

import com.figures.figures.exceptions.InvalidFigureParametersException;
import com.figures.figures.models.CreateFigureCommand;
import com.figures.figures.models.Figure;
import org.springframework.context.annotation.Configuration;

@Configuration("SQUARE")
public class CreateSquareStrategy implements CreateFigureStrategy {

    @Override
    public Figure create(CreateFigureCommand command) {
        if (!(command.getParameters().containsKey("side"))) {
            throw new InvalidFigureParametersException("Wrong parameters!");
        }
        return Figure.builder()
                .type(command.getType())
                .parameters(command.getParameters())
                .build();
    }
}
