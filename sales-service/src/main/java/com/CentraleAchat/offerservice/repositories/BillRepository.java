package com.CentraleAchat.offerservice.repositories;

import com.CentraleAchat.offerservice.entities.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill,Long> {
}
