package com.corona.orderdelivery.delivery.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY, reason="No route planned for delivery yet.")
public class RouteNotFoundException extends RuntimeException{
}
