package com.CentraleAchat.offerservice.services;


import com.CentraleAchat.offerservice.entities.*;
import com.CentraleAchat.offerservice.repositories.BillRepository;
import com.CentraleAchat.offerservice.repositories.OrderLineRepository;
import com.CentraleAchat.offerservice.repositories.OrderRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class OrderServiceImp implements OrderService {

    OrderLineRepository orderLineRepository;

    OrderRepository orderRepository;

    APIInventoryService apiInventoryService;

    APIUserService apiUserService;

    BillRepository billRepository;

    @Transactional
    public Order sendOrder(Order order, Long idClient) {
        List<OrderLine> orderLines=order.getOrderLines();
        float total = 0;
        for (OrderLine ol :orderLines) {
            float price=apiInventoryService.GetPriceProductByIdProduct(ol.getIdProduct());
           apiInventoryService.commanderProduct(ol.getIdProduct(),ol.getQuantity());
        total+=price*ol.getQuantity();
        ol.setOrder(order);
            orderLineRepository.save(ol);
        }

        order.setIdClient(idClient);
        order.setTotal(total);
       // order.setClientName(apiUserService.getClientNameById(idClient));
        order.setStatus(OrderStatus.PENDING);
        return orderRepository.save(order);
    }

    @Transactional
    public Order confirmerOrder(Long idOrder) {
       Order order=orderRepository.findById(idOrder).get();
      Bill bill=new Bill();
       order.setStatus(OrderStatus.COMPLETED);
        float HTVA = 0;
        float TVA = 0;
        List<OrderLine> orderLines=order.getOrderLines();
        for (OrderLine ol :orderLines) {
            float price=apiInventoryService.GetPriceProductByIdProduct(ol.getIdProduct());
            float priceTVA = price+(price*19)/100;
            HTVA+=price*ol.getQuantity();
            TVA+=priceTVA*ol.getQuantity()-price*ol.getQuantity();
        }
        float totalTTC=HTVA+TVA;

         bill.setHTVA(HTVA);
         bill.setTVA(TVA);
         bill.setTotalTTC(totalTTC);
         bill.setIdClient(order.getIdClient());
         bill.setBillType(BillType.NORMAL);
         order.setBill(bill);
         billRepository.save(bill);

        return orderRepository.save(order);
    }

    @Transactional
    public Order retournerOrder(Long idOrder) {
        Order order=orderRepository.findById(idOrder).get();
        if (order.getStatus()==OrderStatus.COMPLETED) {
            order.setStatus(OrderStatus.RETURNED);
            List<OrderLine> orderLines = order.getOrderLines();
            for (OrderLine ol : orderLines) {

                apiInventoryService.annulerOrder(ol.getIdProduct(), ol.getQuantity());
            }
            order.getBill().setBillType(BillType.RETURN);

         }else if (order.getStatus()==OrderStatus.PENDING){
        log.info("order n'est completed");
        }else if (order.getStatus()==OrderStatus.CANCELED){
            log.info("order est deja cancled");
    }else {
        log.info("order est dejà returned");

    }
        return orderRepository.save(order);
    }

    @Transactional
    public Order denyOrder(Long idOrder) {
        Order order=orderRepository.findById(idOrder).get();
        if (order.getStatus()==OrderStatus.PENDING) {
            order.setStatus(OrderStatus.CANCELED);
            List<OrderLine> orderLines=order.getOrderLines();
            for (OrderLine ol :orderLines) {

                apiInventoryService.annulerOrder(ol.getIdProduct(), ol.getQuantity());

            }

        }else if (order.getStatus()==OrderStatus.COMPLETED){
            log.info("order est completed");

        }else if (order.getStatus()==OrderStatus.CANCELED){
            log.info("order est dejà cancled");


        }else {
            log.info("order est n'est pas en cours");

        }

            return orderRepository.save(order);
    }


    @Override
    public Order updateOrder(Order order) {
       return orderRepository.save(order);
    }


    @Override
    public List<Order> retrieveAllOrder() {
        return orderRepository.findAll();
    }
}

