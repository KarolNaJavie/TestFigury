package com.figures.figures.service;

import com.figures.figures.exceptions.UnknownFigureTypeException;
import com.figures.figures.models.CreateFigureCommand;
import com.figures.figures.models.Figure;
import com.figures.figures.models.FigureDTO;
import com.figures.figures.models.FilterRequest;
import com.figures.figures.repository.FigureRepository;
import com.figures.figures.specifiactionBuilder.FigureSpecificationBuilder;
import com.figures.figures.strategies.CreateFigureStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class FigureService {

    private final FigureRepository figureRepository;
    private final Map<String, CreateFigureStrategy> createFigureStrategies;
    private final FigureSpecificationBuilder figureSpecificationBuilder;

    public FigureDTO create(CreateFigureCommand command) {
        CreateFigureStrategy strategy = createFigureStrategies.get(command.getType().toUpperCase());
        if (strategy == null) {
            throw new UnknownFigureTypeException(
                    "Unknown figure type: " + command.getType()
            );
        }
        return FigureDTO.fromEntity(figureRepository.save(strategy.create(command)));
    }

    public Page<FigureDTO> getAll(Pageable pageable) {
        return figureRepository.findAll(pageable).map(FigureDTO::fromEntity);
    }

    public Page<FigureDTO> filter(FilterRequest filterRequest, Pageable pageable) {
        Specification<Figure> specification = figureSpecificationBuilder
                .buildSpecification(filterRequest.getParams());
        return figureRepository.findAll(specification, pageable).map(FigureDTO::fromEntity);
    }
}


