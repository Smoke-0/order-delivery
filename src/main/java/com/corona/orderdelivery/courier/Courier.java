package com.corona.orderdelivery.courier;

import com.corona.orderdelivery.delivery.Delivery;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "couriers")
public class Courier {
    @Id
    @GeneratedValue(generator = "inc")
    @GenericGenerator(name="inc", strategy="increment")
    @Column(name = "courier_id")
    private Integer id;

    private String firstName;
    private String lastName;

    @OneToMany
    @JoinColumn(name="courier_id")
    private List<Delivery> deliveries;

    public Courier() {
    }

    public List<Delivery> getDeliveries() {
        return deliveries;
    }

    public Courier(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
