package ru.alerto.rostov.controllers;

import org.springframework.web.bind.annotation.*;
import ru.alerto.rostov.enums.CustomerType;
import ru.alerto.rostov.enums.Product;
import ru.alerto.rostov.scripts.CustomerPreferences;
import ru.alerto.rostov.scripts.PathFinder;
import ru.alerto.rostov.models.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class MainController {

    private static final Random random = new Random();
    private static final int TIME_TO_CHOOSE_PRODUCT_MS = 5000;
    private static final int TIME_TO_MOVE_BETWEEN_POINTS_MS = 1000;
    private static final int TIME_AT_CASH_REGISTER_MS = 10000;

    @PostMapping("/api/v1/simulation")
    public List<SimulationResponse> startSimulation(@RequestBody SimulationRequest request) {
        List<SimulationResponse> responses = new ArrayList<>();
        long currentTime = request.getStartTime();
        long endTime = request.getEndTime();

        // Счетчик для поочередного выбора кассы
        AtomicInteger cashRegisterCounter = new AtomicInteger(0);

        while (currentTime < endTime) {
            long interval = (1 + random.nextInt(10)) * 60 * 1000;
            currentTime += interval;

            if (currentTime > endTime) break;

            CustomerType customerType = getRandomCustomerType();
            List<Product> purchases = generatePurchases(customerType);

            SimulationResponse response = generateCustomerRoute(
                    request.getEntryPoint(),
                    purchases,
                    request.getShelves(),
                    request.getObstacles(),
                    request.getCashRegisters(),
                    cashRegisterCounter,
                    currentTime
            );

            responses.add(response);
        }

        return responses;
    }

    private SimulationResponse generateCustomerRoute(
            Point entryPoint,
            List<Product> purchases,
            List<Shelf> shelves,
            Set<Point> obstacles,
            List<Point> cashRegisters,
            AtomicInteger cashRegisterCounter,
            long arrivalTime
    ) {
        SimulationResponse response = new SimulationResponse();
        response.setTime(arrivalTime);

        List<SimulationResponse.RouteStep> route = new ArrayList<>();
        Point currentPosition = entryPoint;

        // Маршрут по полкам с товарами
        for (Product product : purchases) {
            List<Shelf> productShelves = shelves.stream()
                    .filter(s -> s.getProductType() == product)
                    .toList();

            if (!productShelves.isEmpty()) {
                Shelf targetShelf = productShelves.get(random.nextInt(productShelves.size()));
                List<Point> pathToShelf = PathFinder.findPath(currentPosition, targetShelf.getPosition(), obstacles);

                if (pathToShelf != null && !pathToShelf.isEmpty()) {
                    for (Point point : pathToShelf) {
                        route.add(new SimulationResponse.RouteStep("go", point, 0));
                        currentPosition = point;
                    }
                    route.add(new SimulationResponse.RouteStep("stay", currentPosition, TIME_TO_CHOOSE_PRODUCT_MS));
                }
            }
        }

        // Маршрут к кассе (если есть кассы и есть покупки)
        if (!cashRegisters.isEmpty() && !purchases.isEmpty()) {
            // Выбираем кассу по очереди
            int cashRegisterIndex = cashRegisterCounter.getAndIncrement() % cashRegisters.size();
            Point targetCashRegister = cashRegisters.get(cashRegisterIndex);

            List<Point> pathToCashRegister = PathFinder.findPath(currentPosition, targetCashRegister, obstacles);

            if (pathToCashRegister != null && !pathToCashRegister.isEmpty()) {
                for (Point point : pathToCashRegister) {
                    route.add(new SimulationResponse.RouteStep("go", point, 0));
                    currentPosition = point;
                }
                // Ожидание на кассе
                route.add(new SimulationResponse.RouteStep("stay", currentPosition, TIME_AT_CASH_REGISTER_MS));
            }
        }

        // Возвращаемся к выходу
        List<Point> pathToExit = PathFinder.findPath(currentPosition, entryPoint, obstacles);
        if (pathToExit != null && !pathToExit.isEmpty()) {
            for (Point point : pathToExit) {
                route.add(new SimulationResponse.RouteStep("go", point, 0));
            }
        }

        response.setRoute(route);
        return response;
    }

    private static CustomerType getRandomCustomerType() {
        CustomerType[] types = CustomerType.values();
        return types[random.nextInt(types.length)];
    }

    private static List<Product> generatePurchases(CustomerType type) {
        List<Product> result = new ArrayList<>();
        Map<Product, Integer> preferences = CustomerPreferences.getPreferences(type);

        for (Map.Entry<Product, Integer> entry : preferences.entrySet()) {
            if (random.nextInt(100) < entry.getValue()) {
                result.add(entry.getKey());
            }
        }

        return result;
    }
}