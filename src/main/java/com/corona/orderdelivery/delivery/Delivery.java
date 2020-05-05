package com.corona.orderdelivery.delivery;

import com.corona.orderdelivery.courier.domain.Courier;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "deliveries")
public class Delivery {
    @Id
    @GeneratedValue(generator="inc")
    @GenericGenerator(name="inc", strategy="increment")
    @Column(name="delivery_id")
    Integer id;

    @ManyToOne
    @JoinColumn(name="courier_id")
    private Courier courier;

    public Route getRoute() {
        return route;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="route_id")
    private Route route;

    public Delivery() {
    }

    public Integer getId() {
        return id;
    }
}
