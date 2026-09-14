package com.figures.figures.service;

import com.figures.figures.exceptions.UnknownFigureTypeException;
import com.figures.figures.models.CreateFigureCommand;
import com.figures.figures.models.Figure;
import com.figures.figures.models.FigureDTO;
import com.figures.figures.models.FilterRequest;
import com.figures.figures.repository.FigureRepository;
import com.figures.figures.strategies.CreateFigureStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FigureService {

    private final FigureRepository figureRepository;
    private final Map<String, CreateFigureStrategy> createFigureStrategies;

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

public Page<FigureDTO> filter(FilterRequest request, Pageable pageable) {
    if (request.getType() != null) {
        return figureRepository.findAllByType(request.getType(), pageable)
                .map(FigureDTO::fromEntity);

    } else if (request.getParameter() != null && request.getValue() != null) {
        Page<Figure> page = figureRepository.findAll(pageable);

        List<FigureDTO> filtered = page.getContent().stream()
                .filter(n -> request.getValue().equals(
                        n.getParameters().get(request.getParameter())
                ))
                .map(FigureDTO::fromEntity)
                .toList();

        return new PageImpl<>(
                filtered,
                pageable,
                filtered.size()
        );

    } else if (request.getParameter() != null) {
        Page<Figure> page = figureRepository.findAll(pageable);

        List<FigureDTO> filtered = page.getContent().stream()
                .filter(n -> n.getParameters().containsKey(request.getParameter()))
                .map(FigureDTO::fromEntity)
                .toList();

        return new PageImpl<>(
                filtered,
                pageable,
                filtered.size()
        );

    } else {
        return figureRepository.findAll(pageable)
                .map(FigureDTO::fromEntity);
    }
}

}
