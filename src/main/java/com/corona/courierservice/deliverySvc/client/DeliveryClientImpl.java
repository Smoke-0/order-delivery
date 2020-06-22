package com.corona.courierservice.deliverySvc.client;

import com.corona.courierservice.deliverySvc.dto.DeliveryDto;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@ConfigurationProperties(prefix = "delivery.svc", ignoreUnknownFields = false)
@Component
public class DeliveryClientImpl implements DeliveryClient {

    private final String DELIVERY_SVC_PATH_V2 = "/korona/v2/delivery/{deliveryId}";
    private String apihost;

    private final RestTemplate restTemplate;

    public DeliveryClientImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public DeliveryDto getDeliveryById(Integer deliveryId) {

        Map<String, Integer> params = new HashMap<>();
        params.put("deliveryId", deliveryId);
        DeliveryDto result = restTemplate.getForObject(apihost + DELIVERY_SVC_PATH_V2, DeliveryDto.class, params);
        return result;
    }

    public void setApihost(String apihost) {
        this.apihost = apihost;
    }

}
