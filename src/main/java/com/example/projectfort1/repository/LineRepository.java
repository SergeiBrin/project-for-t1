package com.example.projectfort1.repository;

import com.example.projectfort1.model.Line;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LineRepository extends JpaRepository<Line, Long> {
    Line findByLineContaining(String line);
}
