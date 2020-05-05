package com.corona.orderdelivery.courier.repository;

import com.corona.orderdelivery.courier.domain.Courier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourierRepository extends JpaRepository<Courier, Integer> {

}
