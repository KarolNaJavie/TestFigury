package com.figures.figures.controller;

import com.figures.figures.models.CreateFigureCommand;
import com.figures.figures.models.FigureDTO;
import com.figures.figures.models.FilterRequest;
import com.figures.figures.service.FigureService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/figures")
public class FigureController {
//Poprawki:


//filter po parametrze: findAll(pageable) pobiera jedną stronę, a Ty filtrujesz ją w pamięci. Co się stanie, gdy pasujący rekord jest
// na stronie 3, a pytam o stronę 0? I czy total w tym PageImpl będzie prawdziwy? Jak zrobić, żeby filtr działał na całej bazie, a nie
// na jednej stronie? podpowiedz: Specification 😉

    private final FigureService figureService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FigureDTO create(@RequestBody CreateFigureCommand command){
        return figureService.create(command);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<FigureDTO> getAll(Pageable pageable){
        return figureService.getAll(pageable);
    }

    @PostMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public Page<FigureDTO> getFiltered(@RequestBody FilterRequest request, Pageable pageable){
        return figureService.filter(request, pageable);
    }
}
