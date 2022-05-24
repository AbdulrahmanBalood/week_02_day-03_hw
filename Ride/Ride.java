package com.example.exd03.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;
import javax.xml.transform.sax.SAXResult;
@AllArgsConstructor @Data
public class Ride {
    @NotNull(message = "Ride ID cannot be null")
    @Size(min = 2,message = "Ride ID must be more than 2 chars")
    private String rideID;
    @NotNull(message = "Ride name cannot be null")
    @Size(min = 4,message = "Ride name must be more then 4")
    private String rideName;
    @NotNull(message = "Ride type cannot be null")
    @Pattern(regexp = "(?i)(Rollercoaster|thriller|water)",message = "Ride type can only be (Rollercoaster, thriller, water)")
    private String rideType;
    @NotNull(message = "Ticket cannot be null")
    @Positive(message = "Ticket number must be positive")
    // @Pattern(regexp = "[0-9]",message = "Ticket number must be a number")
    private Integer tickets;
    @NotNull(message = "Price cannot be null")
    @Positive(message = "Price cannot be negative")
    //@Pattern(regexp = "[0-9]",message = "Ticket number must be a number")
    private Integer price;
}
