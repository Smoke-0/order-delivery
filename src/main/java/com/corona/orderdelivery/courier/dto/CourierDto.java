package com.corona.orderdelivery.courier.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourierDto {
    private Integer id;

    private String firstName;
    private String lastName;

    private List<DeliveryDto> deliveries;
}
