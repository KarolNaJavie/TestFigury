package com.figures.figures.repository;

import com.figures.figures.models.Figure;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FigureRepository extends JpaRepository<Figure, Long>, JpaSpecificationExecutor<Figure> {
}
