package com.corona.orderdelivery.delivery;

import com.corona.orderdelivery.courier.Courier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Integer> {

}
