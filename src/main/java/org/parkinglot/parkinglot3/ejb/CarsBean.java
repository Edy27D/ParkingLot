package org.parkinglot.parkinglot3.ejb;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.parkinglot.parkinglot3.common.CarDto;
import org.parkinglot.parkinglot3.entities.Car;

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
