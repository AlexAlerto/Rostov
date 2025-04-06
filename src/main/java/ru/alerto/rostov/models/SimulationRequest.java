package ru.alerto.rostov.models;

import lombok.Data;
import java.util.Set;
import java.util.List;

@Data
public class SimulationRequest {
    private Point entryPoint;
    private long startTime; // timestamp
    private long endTime;   // timestamp
    private Set<Point> obstacles;
    private List<Shelf> shelves;
    private List<Point> cashRegisters; // Добавляем список касс
    private int productSelectionTimeMs;
}