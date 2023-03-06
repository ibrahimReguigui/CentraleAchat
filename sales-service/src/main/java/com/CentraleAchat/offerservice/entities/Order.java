package com.CentraleAchat.offerservice.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "commande")
public class Order extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrder;
    @NotEmpty(message = "DeadLine is manadatory")
    @Temporal(TemporalType.DATE)
    private Date deadLine;
    @Size(message="Total must be positive")
    private float total;
    private String idOperator;
    private String idClient;
    private String clientName;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @OneToMany(mappedBy = "order")
    private List<OrderLine> orderLines;

    @OneToOne(cascade = CascadeType.ALL)
    private Bill bill;

}
