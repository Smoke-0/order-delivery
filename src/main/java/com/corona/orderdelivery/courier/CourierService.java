package com.corona.orderdelivery.courier;

import com.corona.orderdelivery.courier.exceptions.DeliveryNotFoundException;
import com.corona.orderdelivery.courier.exceptions.RouteNotFoundException;
import com.corona.orderdelivery.delivery.Delivery;
import com.corona.orderdelivery.delivery.Route;
import com.corona.orderdelivery.delivery.exceptions.NoCouriersFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
class CourierService {
    private final Logger logger = LoggerFactory.getLogger(CourierService.class);
    private final Integer MAX_DELIVERY_TIME = 10;

    private CourierRepository courierRepository;

    public CourierService(CourierRepository courierRepository) {
        this.courierRepository = courierRepository;
    }

    Courier assignCourierToDelivery(Delivery delivery){
        final String uri = "http://localhost:8080/corona/deliveries/{deliveryId}/routes";

        Map<String, Object> params = new HashMap<>();
        params.put("deliveryId", delivery.getId());

        //Calling deliveries API to retrieve delivery/route information - idea is to verify whether any courier has capacity to handle it at given date (i.e. not to spend over 8h on deliveries)
        RestTemplate restTemplate = new RestTemplate();
        try {
            Route route = restTemplate.getForObject(uri, Route.class, params);
            Courier leastBusyCourier = getLeastBusyCourier();
            if(checkDeliveryFeasibility(leastBusyCourier, route)) {
                leastBusyCourier.getDeliveries().add(delivery);
                courierRepository.save(leastBusyCourier);
                return leastBusyCourier;
            }
            else throw new NoCouriersFoundException();
        }
        catch(HttpClientErrorException.NotFound nfe){
            throw new DeliveryNotFoundException();
        }
        catch(HttpClientErrorException.UnprocessableEntity uee) {
            throw new RouteNotFoundException();
        }
    }

    private Courier getLeastBusyCourier(){
        List<Courier> couriers = courierRepository.findAll();
        Optional<Courier> leastBusyCourier = couriers.stream().reduce((courier1, courier2) ->
        getCourierPlannedDeliveryTimes(courier1) < getCourierPlannedDeliveryTimes(courier2) ? courier1 : courier2);
        if(leastBusyCourier.isPresent()) {
            return leastBusyCourier.get();
        }
        else {
            logger.info("No courier found at all.");
            throw new NoCouriersFoundException();
        }
    }

    private boolean checkDeliveryFeasibility(Courier courier, Route deliveryRoute) {
        logger.info("Feasibility check for courier: " + courier.getLastName());
        logger.info("Courier current assignments: " + getCourierPlannedDeliveryTimes(courier));
        logger.info("Route length: " + deliveryRoute.getLength());
        return (getCourierPlannedDeliveryTimes(courier) + deliveryRoute.getLength()) <= MAX_DELIVERY_TIME;
    }

    private Integer getCourierPlannedDeliveryTimes(Courier courier){
        return courier.getDeliveries().stream()
                .map(delivery -> delivery.getRoute().getLength())
                .reduce(0, Integer::sum);
                }
}
