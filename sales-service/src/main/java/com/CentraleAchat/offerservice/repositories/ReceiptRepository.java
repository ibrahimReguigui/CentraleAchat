package com.CentraleAchat.offerservice.repositories;

import com.CentraleAchat.offerservice.entities.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceiptRepository extends JpaRepository<Receipt,Long> {
}
