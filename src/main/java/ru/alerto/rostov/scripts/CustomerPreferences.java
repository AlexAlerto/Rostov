package ru.alerto.rostov.scripts;

import ru.alerto.rostov.enums.CustomerType;
import ru.alerto.rostov.enums.Product;

import java.util.EnumMap;
import java.util.Map;

public class CustomerPreferences {
    private static final Map<CustomerType, Map<Product, Integer>> preferences = new EnumMap<>(CustomerType.class);

    static {
        initializePreferences();
    }

    private static void initializePreferences() {
        // Студент
        Map<Product, Integer> student = new EnumMap<>(Product.class);
        student.put(Product.BREAD, 70);
        student.put(Product.PASTA, 90);
        student.put(Product.SNACKS, 95);
        student.put(Product.BEER, 80);
        student.put(Product.SODA, 85);
        student.put(Product.CHIPS, 90);
        preferences.put(CustomerType.STUDENT, student);

        // Пенсионер
        Map<Product, Integer> pensioner = new EnumMap<>(Product.class);
        pensioner.put(Product.BREAD, 95);
        pensioner.put(Product.DAIRY, 90);
        pensioner.put(Product.BUTTER, 85);
        pensioner.put(Product.CEREALS, 75);
        pensioner.put(Product.TEA, 80);
        preferences.put(CustomerType.PENSIONER, pensioner);

        // Занятая мама
        Map<Product, Integer> busyMom = new EnumMap<>(Product.class);
        busyMom.put(Product.BABY_FOOD, 90);
        busyMom.put(Product.DAIRY, 85);
        busyMom.put(Product.BREAD, 80);
        busyMom.put(Product.JUICES, 75);
        busyMom.put(Product.COOKIES, 70);
        preferences.put(CustomerType.BUSY_MOM, busyMom);

        // Поклонник ЗОЖ
        Map<Product, Integer> healthyLife = new EnumMap<>(Product.class);
        healthyLife.put(Product.SPICES, 80);
        healthyLife.put(Product.SEEDS, 75);
        healthyLife.put(Product.JUICES, 85);
        healthyLife.put(Product.FISH, 70);
        healthyLife.put(Product.TEA, 90);
        preferences.put(CustomerType.HEALTHY_LIFE, healthyLife);

        // Алкоголик
        Map<Product, Integer> alcoholic = new EnumMap<>(Product.class);
        alcoholic.put(Product.BEER, 99);
        alcoholic.put(Product.WINE, 95);
        alcoholic.put(Product.SNACKS, 80);
        alcoholic.put(Product.BREAD, 60);
        preferences.put(CustomerType.ALCOHOLIC, alcoholic);

        // Тусовщик
        Map<Product, Integer> partyGoer = new EnumMap<>(Product.class);
        partyGoer.put(Product.BEER, 90);
        partyGoer.put(Product.CHIPS, 85);
        partyGoer.put(Product.SODA, 80);
        partyGoer.put(Product.SNACKS, 75);
        preferences.put(CustomerType.PARTY_GOER, partyGoer);

        // Домашний кулинар
        Map<Product, Integer> chef = new EnumMap<>(Product.class);
        chef.put(Product.SPICES, 95);
        chef.put(Product.FLOUR, 90);
        chef.put(Product.BUTTER, 85);
        chef.put(Product.MEAT_PRODUCTS, 75);
        preferences.put(CustomerType.CHEF, chef);

        // Турист
        Map<Product, Integer> tourist = new EnumMap<>(Product.class);
        tourist.put(Product.CANNED_GOODS, 90);
        tourist.put(Product.SNACKS, 85);
        tourist.put(Product.COLD_DRINKS, 95);
        tourist.put(Product.BREAD, 80);
        preferences.put(CustomerType.TOURIST, tourist);
    }

    public static Map<Product, Integer> getPreferences(CustomerType type) {
        return preferences.get(type);
    }
}