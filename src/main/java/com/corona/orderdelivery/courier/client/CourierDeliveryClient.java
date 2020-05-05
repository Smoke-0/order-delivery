package com.corona.orderdelivery.courier.client;

import com.corona.orderdelivery.courier.dto.RouteDto;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@Service
//@ConfigurationProperties(value = "corona", ignoreUnknownFields = false)
public class CourierDeliveryClient {

//    private String apihost;
    private String apihost = "http://localhost:8080";
    private final String DELIVERY_API_PATH = "/corona/deliveries/{deliveryId}/routes";
    private final RestTemplate restTemplate;

    public CourierDeliveryClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public RouteDto getDeliveryRoute(String deliveryId){

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(apihost + DELIVERY_API_PATH)
                .queryParam("deliveryId", deliveryId);
        Map<String, String> params = new HashMap<>();
        params.put("deliveryId", deliveryId);
        return restTemplate.getForObject(apihost + DELIVERY_API_PATH, RouteDto.class, params);
//        return restTemplate.getForObject(builder.toUriString(), RouteDto.class);
    }
}
