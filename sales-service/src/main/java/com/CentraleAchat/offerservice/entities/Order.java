package com.CentraleAchat.offerservice.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Order extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrder;
    @OneToMany
    private List<OrderLine> orderLines;
    private Date deadLine;
    private float total;
    private Long idOperator;
    private Long idClient;
    @OneToMany
    private List<Bill> bills;
}
