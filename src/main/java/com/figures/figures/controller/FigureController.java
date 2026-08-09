package com.figures.figures.controller;

import com.figures.figures.models.CreateFigureCommand;
import com.figures.figures.models.FigureDTO;
import com.figures.figures.models.FilterRequest;
import com.figures.figures.service.FigureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/figures")
public class FigureController {

    private final FigureService figureService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FigureDTO create(@RequestBody CreateFigureCommand command){
        return figureService.create(command);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<FigureDTO> getAll(){
        return figureService.getAll();
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<FigureDTO> getFiltered(@RequestBody FilterRequest request){
        return figureService.filter(request);
    }
}
