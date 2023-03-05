package com.CentraleAchat.offerservice.repositories;

import com.CentraleAchat.offerservice.entities.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {

    // Find a bill by its codeBill
    Bill findByCodeBill(String codeBill);

    // other methods...
}