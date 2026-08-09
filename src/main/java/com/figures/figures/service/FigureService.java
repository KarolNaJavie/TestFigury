package com.figures.figures.service;

import com.figures.figures.models.CreateFigureCommand;
import com.figures.figures.models.FigureDTO;
import com.figures.figures.models.FilterRequest;
import com.figures.figures.repository.FigureRepository;
import com.figures.figures.strategies.CreateFigureStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FigureService {

    private final FigureRepository figureRepository;
    private final Map<String, CreateFigureStrategy> createFigureStrategies;

    public FigureDTO create(CreateFigureCommand command) {
        CreateFigureStrategy strategy = createFigureStrategies.get(command.getType());
        return FigureDTO.fromEntity(figureRepository.save(strategy.create(command)));
    }

    public List<FigureDTO> getAll() {
        return figureRepository.findAll().stream().map(FigureDTO::fromEntity).toList();
    }

    public List<FigureDTO> filter(FilterRequest request) {
        if (request.getType() != null) {
            return figureRepository.findAllByType(request.getType());
        } else if (request.getParameter() != null) {
            return figureRepository.findAll().stream().filter(n -> n.getParameters().containsKey(request.getParameter())).map(FigureDTO::fromEntity).toList();
        } else {
            return figureRepository.findAll().stream().map(FigureDTO::fromEntity).toList();
        }
    }
}
