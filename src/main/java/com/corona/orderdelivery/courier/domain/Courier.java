package com.corona.orderdelivery.courier.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    @JsonManagedReference
    @OneToMany
    @JoinColumn(name="courier_id")
    private List<CourierDelivery> deliveries;
}
