package com.corona.orderdelivery.courier.repository;

import com.corona.orderdelivery.courier.domain.CourierDelivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourierDeliveryRepository extends JpaRepository<CourierDelivery, Integer> {

}
