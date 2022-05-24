package com.example.exd03.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.*;

@AllArgsConstructor @Data
public class Employee {
    @NotNull(message = "ID cannot be null")
    @Size(min = 2,message = "id length must be more than 2")
    private String id;
    @NotNull(message = "Name cannot be null")
    @Size(min = 4,message = "Name has to be more than 4 in length")
    private String name;
    @NotNull
    @Min(value = 25,message = "Age had to be more than 25")
    private Integer age;
    @AssertFalse(message = "onLeave must b false")
    private boolean onLeave;
    @NotNull(message = "employmentYear cannot be null")
    @Min(value = 4,message = "")
    private Integer employmentYear;
    @NotNull(message = "cannot be null")
    private Integer  annualLeave;
}
