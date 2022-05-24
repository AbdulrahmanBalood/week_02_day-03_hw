package com.example.exd03.controller;

import com.example.exd03.model.Employee;
import com.example.exd03.model.ResponseAPI;
import com.example.exd03.model.Ride;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/employee")
public class employeeController {
    ArrayList<Employee> employees = new ArrayList<>();

    @GetMapping
    public ResponseEntity getEmployee() {
        return ResponseEntity.status(200).body(employees);
    }

    @PostMapping("/add")
    public ResponseEntity addEmployee(@RequestBody @Valid Employee employee, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ResponseAPI(message, 400, errors.getFieldError()));
        }
        employees.add(employee);
        return ResponseEntity.status(200).body("Employee added");
    }
    @PutMapping("/update")
    public ResponseEntity updateEmployee(@RequestBody @Valid Employee employee, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ResponseAPI(message,400,errors.getFieldError()));
        }
        for(int i = 0; i < employees.size() ; i++){
            if (employee.getId().equals(employees.get(i).getId())){
                employees.set(i,employee);
                return ResponseEntity.status(200).body("Employee updated");
            }

        }
        return ResponseEntity.status(400).body("Employee not found");
    }
    @DeleteMapping("/delete")
    public ResponseEntity deleteRide(@RequestBody String id){

        for(int i = 0; i < employees.size() ; i++){

            if (id.equals(employees.get(i).getId())){
                employees.remove(i);
                return ResponseEntity.status(200).body("Ride deleted");
            }

        }
        return ResponseEntity.status(400).body("Ride not found");
    }
    @PutMapping(("/apply"))
    public ResponseEntity applyEmployee(@RequestBody String id){
        for(int i = 0; i < employees.size() ; i++){
            Employee e1 = employees.get(i);
            if(id.equals(e1.getId())){
                if(e1.isOnLeave()){
                    return ResponseEntity.status(400).body("Employee is already on leave");
                }
                if(e1.getAnnualLeave() > 0){
                    e1.setOnLeave(true);
                    return ResponseEntity.status(200).body("Employee is now on leave");
                }
            }
        }
        return ResponseEntity.status(400).body("Couldn't find Employee by ID");
    }

}
