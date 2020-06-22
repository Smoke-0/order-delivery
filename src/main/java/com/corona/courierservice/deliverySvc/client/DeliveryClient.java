package com.corona.courierservice.deliverySvc.client;

import com.corona.courierservice.deliverySvc.dto.DeliveryDto;

public interface DeliveryClient {
    DeliveryDto getDeliveryById(Integer deliveryId);
}
