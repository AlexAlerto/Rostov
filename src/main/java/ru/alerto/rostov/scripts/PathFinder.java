package ru.alerto.rostov.scripts;

import ru.alerto.rostov.models.Point;

import java.util.*;

public class PathFinder {

    public static List<Point> findPath(Point start, Point end, Set<Point> obstacles) {
        PriorityQueue<Point> openList = new PriorityQueue<>(Comparator.comparingDouble(p -> p.f));
        Set<Point> closedList = new HashSet<>();

        start.g = 0;
        start.h = calculateH(start, end);
        start.f = start.g + start.h;
        openList.add(start);

        while (!openList.isEmpty()) {
            Point current = openList.poll();
            closedList.add(current);

            if (current.equals(end)) {
                return reconstructPath(current);
            }

            for (Point neighbor : getNeighbors(current, obstacles)) {
                if (closedList.contains(neighbor)) {
                    continue;
                }

                double tentativeG = current.g + 1; // Предполагаем, что все шаги стоят 1

                if (!openList.contains(neighbor) || tentativeG < neighbor.g) {
                    neighbor.parent = current;
                    neighbor.g = tentativeG;
                    neighbor.h = calculateH(neighbor, end);
                    neighbor.f = neighbor.g + neighbor.h;

                    if (!openList.contains(neighbor)) {
                        openList.add(neighbor);
                    }
                }
            }
        }

        return null; // Путь не найден
    }
    private static List<Point> reconstructPath(Point end) {
        List<Point> path = new ArrayList<>();
        Point current = end;

        while (current != null) {
            path.add(current);
            current = current.parent;
        }

        Collections.reverse(path);
        return path;
    }

    private static double calculateH(Point a, Point b) {
        // Евклидово расстояние в качестве эвристики
        return Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
    }

    private static List<Point> getNeighbors(Point p, Set<Point> obstacles) {
        List<Point> neighbors = new ArrayList<>();
        int[] dx = {-1, 1, 0, 0}; // Влево, вправо
        int[] dy = {0, 0, -1, 1}; // Вверх, вниз

        for (int i = 0; i < 4; i++) {
            int newX = p.x + dx[i];
            int newY = p.y + dy[i];

            // Проверяем, что точка в пределах сетки и не является препятствием
            if (newX >= 1 && newX <= 20 && newY >= 1 && newY <= 20) {
                Point neighbor = new Point(newX, newY);
                if (!obstacles.contains(neighbor)) {
                    neighbors.add(neighbor);
                }
            }
        }

        return neighbors;
    }
}