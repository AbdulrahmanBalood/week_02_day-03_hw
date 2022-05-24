package com.example.exd03.controller;

import com.example.exd03.model.BuyRide;
import com.example.exd03.model.ResponseAPI;
import com.example.exd03.model.Ride;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/ride")
public class rideController {

    ArrayList<Ride> rides = new ArrayList<>();
    @GetMapping("")
    public ResponseEntity getRides(){
        return ResponseEntity.status(200).body(rides);
    }
    @PostMapping("/add")
    public ResponseEntity addRide(@RequestBody @Valid Ride ride, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ResponseAPI(message,400,errors.getFieldError()));
        }
        rides.add(ride);
        return ResponseEntity.status(200).body("Ride added");
    }
    @PutMapping("/update")
    public ResponseEntity updateRide(@RequestBody @Valid Ride ride,Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ResponseAPI(message,400,errors.getFieldError()));
        }
        for(int i = 0; i < rides.size() ; i++){
            if (ride.getRideID().equals(rides.get(i).getRideID())){
                rides.set(i,ride);
                return ResponseEntity.status(200).body("Ride updated");
            }

        }
        return ResponseEntity.status(400).body("Ride not found");
    }
    @DeleteMapping("/delete")
    public ResponseEntity deleteRide(@RequestBody String id){

        for(int i = 0; i < rides.size() ; i++){

            if (id.equals(rides.get(i).getRideID())){
                rides.remove(i);
                return ResponseEntity.status(200).body("Ride deleted");
            }

        }
        return ResponseEntity.status(400).body("Ride not found");
    }
    @PutMapping("/sellride")
    public ResponseEntity sellRide(@RequestBody @Valid BuyRide buy){

        for(int i = 0; i < rides.size() ; i++){
            if(buy.getBuyRideID().equals(rides.get(i).getRideID())){
                if(rides.get(i).getTickets() <= 0){
                    return ResponseEntity.status(400).body("Tickets sold out");
                }
                if(buy.getRidePrice() == rides.get(i).getPrice()){
                    rides.get(i).setTickets(rides.get(i).getTickets()-1);
                    return ResponseEntity.status(200).body("Purchased Successfully");
                }
                return ResponseEntity.status(400).body("Ticket price doesn't match the input");
            }
        }
        return ResponseEntity.status(400).body("Invalid ride ID");
    }


}
