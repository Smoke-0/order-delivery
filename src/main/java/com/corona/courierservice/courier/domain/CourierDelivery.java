package com.corona.courierservice.courier.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "courier_deliveries")
public class CourierDelivery {
    @Id
    @Column(name="delivery_id", updatable = false)
    private Integer id;

    @Column(name="route_length")
    private Integer routeLength;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="courier_id")
    private Courier courier;
}
