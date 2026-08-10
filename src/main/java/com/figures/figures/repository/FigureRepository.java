package com.figures.figures.repository;

import com.figures.figures.models.Figure;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FigureRepository extends JpaRepository<Figure, Long>{
    Page<Figure> findAllByType(String type, Pageable pageable);
}
