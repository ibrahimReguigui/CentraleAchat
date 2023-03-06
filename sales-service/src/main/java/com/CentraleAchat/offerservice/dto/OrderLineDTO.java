package com.CentraleAchat.offerservice.dto;

import com.CentraleAchat.offerservice.entities.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class OrderLineDTO {
    private Long idOrderLine;
    private Long idProduct;
    private int quantity;
    private Date deadLine;
    private Long idOrder;
    @Size(message="Total must be positive")
    private float total;
    private Long idClient;
    private String clientName;
    private OrderStatus status;
    private ProductDTO productDTO;


    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
}
