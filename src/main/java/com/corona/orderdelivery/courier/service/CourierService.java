package com.corona.orderdelivery.courier.service;

import com.corona.orderdelivery.courier.client.CourierDeliveryClient;
import com.corona.orderdelivery.courier.domain.Courier;
import com.corona.orderdelivery.courier.domain.CourierDelivery;
import com.corona.orderdelivery.courier.domain.exceptions.CourierNotFoundException;
import com.corona.orderdelivery.courier.dto.CourierDto;
import com.corona.orderdelivery.courier.dto.CourierIdDto;
import com.corona.orderdelivery.courier.dto.RouteDto;
import com.corona.orderdelivery.courier.mappers.CourierMapper;
import com.corona.orderdelivery.courier.repository.CourierRepository;
import com.corona.orderdelivery.delivery.Delivery;
import com.corona.orderdelivery.delivery.exceptions.NoCouriersFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CourierService {
    private final Integer MAX_DELIVERY_TIME = 10;

    private final CourierRepository courierRepository;
    private final CourierDeliveryClient courierDeliveryClient;
    private final CourierDeliveryService courierDeliveryService;

    public CourierService(CourierRepository courierRepository,
                          CourierDeliveryClient courierDeliveryClient,
                          CourierDeliveryService courierDeliveryService) {
        this.courierRepository = courierRepository;
        this.courierDeliveryClient = courierDeliveryClient;
        this.courierDeliveryService = courierDeliveryService;
    }

    public List<CourierDto> getAllCouriers(){
        return courierRepository.findAll().stream()
                .map(courier -> CourierMapper.INSTANCE.courierToCourierDto(courier))
                .collect(Collectors.toList());
    }

    public CourierIdDto assignCourierToDelivery(String deliveryId){

        RouteDto route = courierDeliveryClient.getDeliveryRoute(deliveryId);

        Courier leastBusyCourier = getLeastBusyCourier();
        if(checkDeliveryFeasibility(leastBusyCourier, route.getLength())) {
            courierDeliveryService.createCourierDelivery(new CourierDelivery().builder()
                .id(deliveryId)
                .routeLength(route.getLength())
                .courier(leastBusyCourier)
                .build());

            return new CourierIdDto().builder().courierId(leastBusyCourier.getId()).build();
        }
        else throw new NoCouriersFoundException();
    }

    private Courier getLeastBusyCourier(){
        List<Courier> couriers = courierRepository.findAll();
        Optional<Courier> leastBusyCourier = couriers.stream().reduce((courier1, courier2) ->
            getCourierPlannedDeliveryTimes(courier1) < getCourierPlannedDeliveryTimes(courier2) ? courier1 : courier2);
        return leastBusyCourier.orElseThrow(NoCouriersFoundException::new);
    }

    private boolean checkDeliveryFeasibility(Courier courier, Integer routeLength) {
        log.info("Feasibility check for courier: " + courier.getLastName());
        log.info("Courier current assignments: " + getCourierPlannedDeliveryTimes(courier));
        log.info("Route length: " + routeLength);
        return (getCourierPlannedDeliveryTimes(courier) + routeLength) <= MAX_DELIVERY_TIME;
    }

    private Integer getCourierPlannedDeliveryTimes(Courier courier){
        return courier.getDeliveries().stream()
                .map(delivery -> delivery.getRouteLength())
                .reduce(0, Integer::sum);
                }

    public URI createNewCourier(CourierDto courierDto) {
        Courier courier = courierRepository.save(CourierMapper.INSTANCE.courierDtoToCourier(courierDto));
        return URI.create("/corona/couriers/" + courier.getId());
    }

    public CourierDto getCourier(Integer courierId) {
        Courier courier = courierRepository.findById(courierId)
                .orElseThrow(CourierNotFoundException::new);
        return CourierMapper.INSTANCE.courierToCourierDto(courier);
    }
}
