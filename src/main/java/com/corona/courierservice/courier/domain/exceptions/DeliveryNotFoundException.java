package com.corona.courierservice.courier.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason="Delivery not found")
public class DeliveryNotFoundException extends RuntimeException{
}
