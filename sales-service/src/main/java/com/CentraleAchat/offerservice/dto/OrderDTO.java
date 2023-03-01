package com.CentraleAchat.offerservice.dto;


import com.CentraleAchat.offerservice.entities.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class OrderDTO {


    private Long idOrder;
    @NotEmpty(message = "DeadLine is manadatory")
    private Date deadLine;
    @Size(message="Total must be positive")
    private float total;
    private Long idOperator;
    private Long idClient;
    private String clientName;
    private OrderStatus status;
    private Long idOrderLine;
    private int quantity;
    private Long idProduct;
    private List<OrderLineDTO> orderLines;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;

}
