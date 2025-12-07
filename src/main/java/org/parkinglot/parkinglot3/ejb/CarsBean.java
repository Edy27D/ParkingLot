package org.parkinglot.parkinglot3.ejb;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.parkinglot.parkinglot3.common.CarDto;
import org.parkinglot.parkinglot3.entities.Car;
import org.parkinglot.parkinglot3.entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class CarsBean {
    private static final Logger LOG = Logger.getLogger(CarsBean.class.getName());


    @PersistenceContext
    EntityManager entityManager;
    public List<CarDto> copyCarsToDto(List<Car> cars) {
        List<CarDto> dtos = new ArrayList<>();
        for (Car car : cars) {
            CarDto carDto = new CarDto(
                    car.getId(),
                    car.getLicensePlate(),
                    car.getParkingSpot(),
                    car.getOwner().getUsername()
            );
            dtos.add(carDto);
        }
        return dtos;
    }
    public CarDto findCarById(Long id) {
        Car car = entityManager.find(Car.class, id);
        return new CarDto(
                car.getId(),
                car.getLicensePlate(),
                car.getParkingSpot(),
                car.getOwner().getUsername()
        );
    }
    public void updateCar(Long id, String licensePlate, String parkingSpot, Long ownerId) {
        Car car = entityManager.find(Car.class, id);

        if (car == null) {
            throw new RuntimeException("Car not found");
        }

        car.setLicensePlate(licensePlate);
        car.setParkingSpot(parkingSpot);

        User owner = entityManager.find(User.class, ownerId);
        if (owner != null) {
            car.setOwner(owner);
        }

        entityManager.merge(car);
    }
    public void deleteCarsByIds(List<Long> carIds) {
        LOG.info("deleteCarsByIds");

        for (Long id : carIds) {
            Car car = entityManager.find(Car.class, id);
            if (car != null) {
                entityManager.remove(car);
            }
        }
    }


    public void createCar(String licensePlate, String parkingSpot, Long ownerId) {
        LOG.info("createCar");

        try {
            Car car = new Car();
            car.setLicensePlate(licensePlate);
            car.setParkingSpot(parkingSpot);

            if (ownerId != null) {
                User owner = entityManager.find(User.class, ownerId);
                if (owner != null) {
                    // legăm în ambele sensuri
                    car.setOwner(owner);
                    owner.getCars().add(car);
                }
            }

            entityManager.persist(car);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<CarDto> findAllCars() {
        LOG.info("Find all cars");
        try{
            TypedQuery<Car> typedQuery = entityManager.createQuery("SELECT c FROM Car c", Car.class);
            List<Car> cars = typedQuery.getResultList();
            return copyCarsToDto(cars);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
