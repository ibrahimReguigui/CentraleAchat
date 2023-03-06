package com.CentraleAchat.offerservice.services;


import com.CentraleAchat.offerservice.entities.Order;
import com.CentraleAchat.offerservice.entities.OrderLine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface OrderService {
    Order sendOrder(Order order, String id);
    Order confirmerOrder(Long idOrder);
    Order denyOrder(Long idOrder);
    Order retournerOrder(Long idOrder) ;
    Order updateOrder(Order order);
    List<Order> retrieveAllOrder();

   //  List<String> calculateClientProductCounts() ;

}
