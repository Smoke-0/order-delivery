package com.corona.courierservice.courier.controller;

import com.corona.courierservice.courier.dto.CourierDto;
import com.corona.courierservice.courier.dto.CourierIdDto;
import com.corona.courierservice.courier.service.CourierService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/corona")
public class CourierController {

    private final CourierService courierService;

    CourierController(CourierService courierService) {
        this.courierService = courierService;
    }

    @GetMapping("/couriers")
    ResponseEntity<List<CourierDto>> getAllCouriers(){
        return ResponseEntity.ok(courierService.getAllCouriers());
    }

    @GetMapping("/couriers/{id}")
    ResponseEntity<CourierDto> getCourierById(@PathVariable(name = "id") Integer courierId){
        return ResponseEntity.ok(courierService.getCourier(courierId));
    }

    @PostMapping("/couriers")
    ResponseEntity<URI> createCourier(@RequestBody CourierDto courier){
        return new ResponseEntity<>(courierService.createNewCourier(courier), HttpStatus.CREATED);
    }

    @PostMapping("/couriers/assignDelivery/{deliveryID}")
    ResponseEntity<CourierIdDto> assignCourierToDelivery(@PathVariable("deliveryID") Integer deliveryID){
        return ResponseEntity.ok(courierService.assignCourierToDelivery(deliveryID));
    }
}
