//package store;

import model.Cars;

import java.util.*;

public class CarStore {
    private Map<Integer, Cars> Cars = new HashMap<>();

    public Optional<Cars> addCar(Car car) {
        int id = cars.size();
        car.setId(id);
        cars.put(id, car);
        return Optional.ofNullable(car);
    }

    public Optional<Car> getCar(int id) {
        return Optional.ofNullable(cars.get(id));
    }

    public Set<Car> getAllCars() {
        return new HashSet<>(cars.values());
    }

    public Optional<Car> updateCar(Car car) {
        int id = car.getId();
        if (cars.containsKey(id)) {
            cars.put(id, car);
            return Optional.ofNullable(car);
        }
        return Optional.empty();
    }

    public boolean deletecar(int id) {
        return cars.remove(id) != null;
    }
}