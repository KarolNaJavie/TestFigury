package com.figures.figures.repository;

import com.figures.figures.models.Figure;
import com.figures.figures.models.FigureDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FigureRepository extends JpaRepository<Figure, Long>{
    List<FigureDTO> findAllByType(String type);
}
