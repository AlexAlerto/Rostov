package ru.alerto.rostov.models;

import lombok.Data;
import ru.alerto.rostov.enums.Product;

@Data
public class Shelf {
    private Point position;
    private Product productType;
}