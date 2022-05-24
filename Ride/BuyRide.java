package com.example.exd03.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.*;

@AllArgsConstructor @Data
public class BuyRide {
    @NotNull(message = "Ride ID cannot be null")
    @Size(min = 2,message = "Ride ID must be more than 2 chars")
    private String buyRideID;
    @NotNull(message = "Price cannot be null")
    @Positive(message = "Price cannot be negative")
    private Integer ridePrice;
}
