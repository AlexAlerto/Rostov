package ru.alerto.rostov.models;

import lombok.Data;
import java.util.List;

@Data
public class SimulationResponse {
    private long time;
    private List<RouteStep> route;

    @Data
    public static class RouteStep {
        private String type; // "go" or "stay"
        private Point coord;
        private int value;   // milliseconds for "stay"

        public RouteStep(String type, Point coord, int value) {
            this.type = type;
            this.coord = coord;
            this.value = value;
        }
    }
}