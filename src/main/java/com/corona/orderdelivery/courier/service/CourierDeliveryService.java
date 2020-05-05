package com.corona.orderdelivery.courier.service;

import com.corona.orderdelivery.courier.domain.CourierDelivery;
import com.corona.orderdelivery.courier.repository.CourierDeliveryRepository;
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
