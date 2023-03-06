package com.CentraleAchat.offerservice.mappers;


import com.CentraleAchat.offerservice.dto.OrderLineDTO;
import com.CentraleAchat.offerservice.entities.Order;
import com.CentraleAchat.offerservice.entities.OrderLine;

public class OrderLineMapper {
    public static OrderLine mapToEntity(OrderLineDTO orderLineDTO) {
        OrderLine orderLine=OrderLine.builder().build();
        Order order=Order.builder().build();
        orderLine.setIdOrderLine(orderLineDTO.getIdOrderLine());
        orderLine.setIdProduct(orderLineDTO.getIdProduct());
        orderLine.setQuantity(orderLineDTO.getQuantity());
        order.setIdOrder(orderLineDTO.getIdOrder());
        order.setTotal(orderLineDTO.getTotal());
        order.setIdClient(orderLineDTO.getIdClient());
        order.setClientName(orderLineDTO.getClientName());
        order.setStatus(orderLineDTO.getStatus());
        orderLine.setCreatedAt(orderLineDTO.getCreatedAt());
        orderLine.setCreatedBy(orderLineDTO.getCreatedBy());
        orderLine.setUpdatedAt(orderLineDTO.getUpdatedAt());
        orderLine.setUpdatedBy(orderLineDTO.getUpdatedBy());
        return  orderLine;
    }

    public static OrderLineDTO mapToDto(OrderLine orderLine) {
        OrderLineDTO orderLineDTO=OrderLineDTO.builder()
                .idOrderLine(orderLine.getIdOrderLine())
                .idProduct(orderLine.getIdProduct())
                .quantity(orderLine.getQuantity())
                .deadLine(orderLine.getOrder().getDeadLine())
                .idOrder(orderLine.getOrder().getIdOrder())
                .total(orderLine.getOrder().getTotal())
                .idClient(orderLine.getOrder().getIdClient())
                .clientName(orderLine.getOrder().getClientName())
                .status(orderLine.getOrder().getStatus())
                .createdAt(orderLine.getCreatedAt())
                .createdBy(orderLine.getCreatedBy())
                .updatedAt(orderLine.getUpdatedAt())
                .updatedBy(orderLine.getUpdatedBy())
                .build();
        return orderLineDTO;
        }
}
