package com.corona.courierservice.courier.service;

import com.corona.courierservice.courier.domain.CourierDelivery;
import com.corona.courierservice.courier.repository.CourierDeliveryRepository;
import org.springframework.stereotype.Service;

@Service
public class CourierDeliveryService {

    private final CourierDeliveryRepository courierDeliveryRepository;

    public CourierDeliveryService(CourierDeliveryRepository courierDeliveryRepository) {
        this.courierDeliveryRepository = courierDeliveryRepository;
    }

    public void createCourierDelivery(CourierDelivery courierDelivery){
        courierDeliveryRepository.save(courierDelivery);
    }
}
