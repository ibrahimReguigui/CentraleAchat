package com.CentraleAchat.offerservice.repositories;

import com.CentraleAchat.offerservice.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
