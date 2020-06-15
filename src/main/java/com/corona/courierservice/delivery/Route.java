package com.corona.courierservice.delivery;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "routes")
public
class Route {
    @Id
    @GeneratedValue(generator="inc")
    @GenericGenerator(name="inc", strategy="increment")
    @Column(name="route_id")
    Integer id;

    private Integer length;

    public Integer getLength() {
        return length;
    }

    @OneToOne(mappedBy = "route")
    private Delivery delivery;
}
