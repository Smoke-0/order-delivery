package com.corona.orderdelivery.courier;

import com.corona.orderdelivery.delivery.Delivery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/corona")
class CourierController {
    private final Logger logger = LoggerFactory.getLogger(CourierController.class);

    private CourierRepository courierRepository;
    private CourierService courierService;

    CourierController(CourierRepository courierRepository, CourierService courierService) {
        this.courierRepository = courierRepository;
        this.courierService = courierService;
    }
    @GetMapping("/couriers")
    ResponseEntity<List<Courier>> getAllCouriers(){
        return ResponseEntity.ok(courierRepository.findAll());
    }

    @GetMapping("/couriers/{id}")
    ResponseEntity<Courier> getCourierById(@PathVariable Integer id){
        if(courierRepository.findById(id).isPresent()){
            return ResponseEntity.ok(courierRepository.findById(id).get());
        }
        else
            return ResponseEntity.notFound().build();
    }

    @PostMapping("/couriers")
    ResponseEntity<Courier> createCourier(@RequestBody Courier courier){
        courierRepository.save(courier);
        return new ResponseEntity<>(courier, HttpStatus.CREATED);
    }

    @PostMapping("/couriers/assignDelivery")
    ResponseEntity<Courier> assignCourierToDelivery(@RequestBody Delivery delivery){
        return ResponseEntity.ok(courierService.assignCourierToDelivery(delivery));
    }
}
