package com.corona.orderdelivery.courier.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason="Courier not found")
public class CourierNotFoundException extends RuntimeException{
}
