package com.corona.courierservice.deliverySvc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItemDto {

    private Integer id;
    private Integer productId;
    private Integer quantity;
    private Integer waypointNo;
    private Integer warehouseId;
}