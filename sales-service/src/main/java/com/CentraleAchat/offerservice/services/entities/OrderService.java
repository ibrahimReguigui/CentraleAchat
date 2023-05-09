package com.CentraleAchat.offerservice.services.entities;


import com.CentraleAchat.offerservice.entities.Order;
import java.util.List;

public interface OrderService {
    Order sendOrder(Order order, String id);
    Order confirmerOrder(Long idOrder);
    Order denyOrder(Long idOrder);
    Order retournerOrder(Long idOrder) ;
    Order updateOrder(Order order);
    List<Order> retrieveAllOrder();


}
