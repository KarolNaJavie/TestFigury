package com.figures.figures.figures;

import com.figures.figures.models.CreateFigureCommand;
import com.figures.figures.models.Figure;
import com.figures.figures.models.FigureDTO;
import com.figures.figures.models.FilterRequest;
import com.figures.figures.repository.FigureRepository;
import com.figures.figures.service.FigureService;
import com.figures.figures.strategies.CreateCircleStrategy;
import com.figures.figures.strategies.CreateFigureStrategy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.Map;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class FiguresServiceTest {

    @InjectMocks
    private FigureService figureService;
    @Mock
    private FigureRepository figureRepository;
    @Mock
    private CreateCircleStrategy createCircleStrategy;
    @Mock
    private Map<String, CreateFigureStrategy> createFigureStrategies;

    @Test
    public void testCreate_HappyPath() {
        CreateFigureCommand command = CreateFigureCommand.builder().type("CIRCLE").build();
        Figure figure = Figure.builder().id(1L).type("CIRCLE").parameters(Map.of("radius", 5.0)).build();

        when(createFigureStrategies.get("CIRCLE")).thenReturn(createCircleStrategy);
        when(createCircleStrategy.create(command)).thenReturn(figure);
        when(figureRepository.save(figure)).thenReturn(figure);

        FigureDTO saved = figureService.create(command);

        assertEquals(figure.getType(), saved.getType());
        verify(createCircleStrategy).create(command);
        verify(figureRepository).save(figure);
    }

    @Test
    void getAllTest() {
        Figure circle = Figure.builder()
                .id(1L)
                .type("CIRCLE")
                .parameters(Map.of("radius", 5.0))
                .build();

        Figure square = Figure.builder()
                .id(2L)
                .type("SQUARE")
                .parameters(Map.of("side", 4.0))
                .build();

        Pageable pageable = PageRequest.of(0, 10);
        Page<Figure> page = new PageImpl<>(
                List.of(circle, square),
                pageable,
                2
        );
        when(figureRepository.findAll(pageable)).thenReturn(page);

        Page<FigureDTO> saved = figureService.getAll(pageable);


        assertThat(saved).hasSize(2);
        assertEquals("CIRCLE", saved.getContent().getFirst().getType());
        verify(figureRepository).findAll(pageable);
    }

//    @Test
//    void searchByTypeTest() {
//        FilterRequest request = FilterRequest.builder()
//                .type("circle")
//                .build();
//
//        Figure circle = Figure.builder()
//                .id(1L)
//                .type("circle")
//                .parameters(Map.of("radius", 5.0))
//                .build();
//
//        Pageable pageable = PageRequest.of(0, 10);
//
//        Page<Figure> page = new PageImpl<>(
//                List.of(circle),
//                pageable,
//                1
//        );
//        when(figureRepository.findAllByType("circle", pageable))
//                .thenReturn(page);
//
//       Page<FigureDTO> result = figureService.filter(request, pageable);
//
//        assertEquals(1, result.getTotalElements());
//        assertEquals(1, result.getTotalPages());
//    }
}
