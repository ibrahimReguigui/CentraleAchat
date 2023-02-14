package com.CentraleAchat.offerservice.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Bill extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBill;
    private Long idClient;
    private Long idSupplier;
    private float TVA;
    private float HTVA;
    private float totalTTC;
    private BillType billType;
    @OneToMany
    private List<AdditionalCharge> additionalCharges;
    @ManyToOne
    private Order order;
}
