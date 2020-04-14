package com.corona.orderdelivery.delivery.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason="No free couriers found to handle the delivery.")
public class NoCouriersFoundException extends RuntimeException{
}
