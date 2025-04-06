package ru.alerto.rostov.enums;

public enum Product {
    BUTTER("Масло"),
    FLOUR("Мука"),
    SUGAR("Сахар"),
    BREAD("Хлеб"),
    WINE("Вино"),
    COLD_DRINKS("Холодные напитки"),
    FISH("Рыба"),
    SAUSAGES("Колбасы"),
    DAIRY("Молочные товары"),
    MEAT_PRODUCTS("Мясные изделия"),
    SPICES("Приправы"),
    SEEDS("Семена"),
    SNACKS("Снеки"),
    DESSERTS("Десерты"),
    PET_FOOD("Корм"),
    HOUSEHOLD("Бытовые товары"),
    JUICES("Соки"),
    BEER("Пиво"),
    CHIPS("Чипсы"),
    COSMETICS("Косметика"),
    CANNED_GOODS("Консервы"),
    BABY_FOOD("Детское питание"),
    CEREALS("Сухие завтраки"),
    COFFEE("Кофе"),
    TEA("Чай"),
    COOKIES("Печенье"),
    SODA("Газировки"),
    PASTA("Макароны");

    private final String name;

    Product(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
