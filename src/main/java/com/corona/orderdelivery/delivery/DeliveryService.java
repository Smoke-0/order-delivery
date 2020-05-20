package com.corona.orderdelivery.delivery;

import com.corona.orderdelivery.delivery.exceptions.DeliveryNotFoundException;
import com.corona.orderdelivery.delivery.exceptions.RouteNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
class DeliveryService {
    private final Logger logger = LoggerFactory.getLogger(DeliveryService.class);

    private DeliveryRepository deliveryRepository;

    public DeliveryService(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    Route getDeliveryRoute(Integer deliveryId){
        logger.info("Delivery Id: " +  deliveryId);
        if(deliveryRepository.findById(deliveryId).isPresent()){
            Route route = deliveryRepository.findById(deliveryId).get().getRoute();
            if(route!=null){
                return route;
            }
            else throw new RouteNotFoundException();
        }
        else throw new DeliveryNotFoundException();
    }

    Optional<Delivery> getDelivery(Integer deliveryId){
        logger.info("Delivery Id: " +  deliveryId);
        Optional<Delivery> delivery = deliveryRepository.findById(deliveryId);
        logger.info(String.valueOf(delivery));
        if(delivery!=null) {
            return delivery;
        }
        else throw new DeliveryNotFoundException();
    }
}
