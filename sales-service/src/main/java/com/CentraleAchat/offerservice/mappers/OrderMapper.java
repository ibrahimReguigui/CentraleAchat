package com.CentraleAchat.offerservice.mappers;

import com.CentraleAchat.offerservice.dto.OrderDTO;
import com.CentraleAchat.offerservice.entities.Order;

public class OrderMapper {
    public static Order mapToEntity(OrderDTO orderDto) {
        Order order=Order.builder().build();
        order.setIdOrder(orderDto.getIdOrder());
        order.setDeadLine(orderDto.getDeadLine());
        order.setTotal(orderDto.getTotal());
        order.setIdClient(orderDto.getIdClient());
        orderDto.setOrderLines(orderDto.getOrderLines());
        order.setClientName(orderDto.getClientName());
        order.setIdOperator(orderDto.getIdOperator());
        order.setStatus(orderDto.getStatus());
        order.setCreatedAt(orderDto.getCreatedAt());
        order.setCreatedBy(orderDto.getCreatedBy());
        order.setUpdatedAt(orderDto.getUpdatedAt());
        order.setUpdatedBy(orderDto.getUpdatedBy());
        return order;
    }

    public static OrderDTO mapToDto(Order order) {
        OrderDTO orderDto= OrderDTO.builder()
                .idOrder(order.getIdOrder())
                .deadLine(order.getDeadLine())
                .total(order.getTotal())
                .idClient(order.getIdClient())
                .idOperator(order.getIdOperator())
                .clientName(order.getClientName())
                .status(order.getStatus())
                .createdAt(order.getCreatedAt())
                .createdBy(order.getCreatedBy())
                .updatedAt(order.getUpdatedAt())
                .updatedBy(order.getUpdatedBy())
                .build();
        return orderDto;
    }
}
