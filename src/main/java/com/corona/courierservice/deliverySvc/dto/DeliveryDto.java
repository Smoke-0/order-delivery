package com.corona.courierservice.deliverySvc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeliveryDto {

    private Integer id;
    private List<OrderItemDto> items;
    private Integer routeLength;


}
