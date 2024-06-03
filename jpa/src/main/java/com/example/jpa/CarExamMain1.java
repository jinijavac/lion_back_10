package com.example.jpa;

import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class CarExamMain1 {
    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
//
//        Car car1 = new Car();
//        car1.setManufacturer("Toyota");
//        car1.setSeatCount(5);
//        em.persist(car1);
//
//        Car car2 = new Car();
//        car2.setManufacturer("hyundai");
//        car2.setSeatCount(4);
//        em.persist(car2);
//
//        Truck truck = new Truck();
//        truck.setManufacturer("kia");
//        truck.setPayloadCapactiy(20.0);
//        em.persist(truck);

//        Car car = em.find(Car.class, 1L);
        List<Vehicle> vehicles = em.createQuery("SELECT v from Vehicle v", Vehicle.class).getResultList();

        for (Vehicle vehicle : vehicles) {
            if(vehicle instanceof Car){
                Car car = (Car)vehicle;
                log.info("Car info ::: {},{}",car.getManufacturer(),car.getSeatCount());
            }else if(vehicle instanceof Truck){
                Truck truck = (Truck) vehicle;
                log.info("Truck info ::: {},{}",truck.getManufacturer(), truck.getPayloadCapacity());
            }
        }

//        log.info("Car info"+car.getManufacturer());
        em.getTransaction().commit();
    }
}
