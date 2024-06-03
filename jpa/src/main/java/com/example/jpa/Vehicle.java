package com.example.jpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@Getter@Setter
public class Vehicle {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String manufacturer;
}
@Entity
@DiscriminatorValue("CAR")
@Getter@Setter
class Car extends Vehicle{
    private int seatCount;
}

@Entity
@DiscriminatorValue("TRUCK")
@Getter@Setter
class Truck extends Vehicle{
    private double payloadCapacity;
}
