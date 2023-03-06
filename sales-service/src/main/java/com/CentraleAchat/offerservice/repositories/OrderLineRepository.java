package com.CentraleAchat.offerservice.repositories;

import com.CentraleAchat.offerservice.entities.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLineRepository extends JpaRepository<OrderLine,Long> {
}
