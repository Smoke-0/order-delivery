package com.corona.courierservice.courier.repository;

import com.corona.courierservice.courier.domain.CourierDelivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourierDeliveryRepository extends JpaRepository<CourierDelivery, Integer> {

}
