package ru.alerto.rostov.enums;

public enum CustomerType {
    STUDENT("Студент"),
    PENSIONER("Пенсионер"),
    BUSY_MOM("Занятая мама"),
    HEALTHY_LIFE("Поклонник ЗОЖ"),
    ALCOHOLIC("Алкоголик"),
    PARTY_GOER("Тусовщик"),
    CHEF("Домашний кулинар"),
    TOURIST("Турист");

    private final String name;

    CustomerType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}