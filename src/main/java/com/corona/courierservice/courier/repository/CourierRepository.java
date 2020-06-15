package com.corona.courierservice.courier.repository;

import com.corona.courierservice.courier.domain.Courier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourierRepository extends JpaRepository<Courier, Integer> {

}
