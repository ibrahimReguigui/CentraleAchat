package com.CentraleAchat.offerservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderLine extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrderLine;
    private Long idProduct;
    private int quantity;

    @JsonIgnore
    @ManyToOne
    private Order order;
}
