package ru.alerto.rostov.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;

public class Point {
    public int x;
    public int y;

    @JsonIgnore
    public Point parent;

    @JsonIgnore
    public double g;

    @JsonIgnore
    public double h;

    @JsonIgnore
    public double f;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Point point = (Point) obj;
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return x + "," + y;
    }
}