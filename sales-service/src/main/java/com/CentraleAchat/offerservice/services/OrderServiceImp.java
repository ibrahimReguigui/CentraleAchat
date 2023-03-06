package com.CentraleAchat.offerservice.services;


import com.CentraleAchat.offerservice.entities.*;
import com.CentraleAchat.offerservice.repositories.BillRepository;
import com.CentraleAchat.offerservice.repositories.OrderLineRepository;
import com.CentraleAchat.offerservice.repositories.OrderRepository;

import com.CentraleAchat.offerservice.services.utilsService.KeycloakService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class OrderServiceImp implements OrderService {

    OrderLineRepository orderLineRepository;

    OrderRepository orderRepository;

    APIInventoryService apiInventoryService;

    APIUserService apiUserService;

    BillRepository billRepository;
    TwilioService twilioService;
    private final Keycloak keycloak;
    private KeycloakService keycloakService;


    @Transactional
    public Order sendOrder(Order order, String idClient) {
        List<OrderLine> orderLines = order.getOrderLines();

        float total = 0;
        for (OrderLine ol : orderLines) {
            float price = apiInventoryService.GetPriceProductByIdProduct(ol.getIdProduct());
            apiInventoryService.commanderProduct(ol.getIdProduct(), ol.getQuantity());
            total += price * ol.getQuantity();
            ol.setOrder(order);
            orderLineRepository.save(ol);
        }
        //
        UserResource userResource = keycloak.realm("pidev").users().get(idClient);
        UserRepresentation client = userResource.toRepresentation();
        //
        twilioService.sendSms("+216" + client.getAttributes().get("phoneNumber").get(0),
                " SALEM Mr " + client.getLastName()+" "+client.getFirstName() + ", Vous receiver un order");
        order.setIdClient(idClient);
        order.setTotal(total);
        order.setClientName(client.getLastName()+" "+client.getFirstName());
        order.setStatus(OrderStatus.PENDING);
        order.setIdOperator(keycloakService.whoAmI().getSubject());
        System.out.println("test");
        return orderRepository.save(order);
    }

    @Transactional
    public Order confirmerOrder(Long idOrder) {
        Order order = orderRepository.findById(idOrder).get();
        Bill bill = new Bill();
        order.setStatus(OrderStatus.COMPLETED);
        float HTVA = 0;
        float TVA = 0;
        List<OrderLine> orderLines = order.getOrderLines();
        for (OrderLine ol : orderLines) {
            float price = apiInventoryService.GetPriceProductByIdProduct(ol.getIdProduct());
            float priceTVA = price + (price * 19) / 100;
            HTVA += price * ol.getQuantity();
            TVA += priceTVA * ol.getQuantity() - price * ol.getQuantity();
        }
        float totalTTC = HTVA + TVA;

        bill.setHTVA(HTVA);
        bill.setTVA(TVA);
        bill.setTotalTTC(totalTTC);
        bill.setIdClient(keycloakService.whoAmI().getSubject());
        bill.setBillType(BillType.NORMAL);
        bill.setBillStatus(BillStatus.NON_PAYEE);
        //
        UserResource userResource = keycloak.realm("pidev").users().get(order.getIdOperator());
        UserRepresentation operateur = userResource.toRepresentation();
        //
        bill.setIdCompany(operateur.getAttributes().get("idCompany").get(0));
        order.setBill(bill);
        billRepository.save(bill);

        return orderRepository.save(order);
    }

    @Transactional
    public Order retournerOrder(Long idOrder) {
        Order order = orderRepository.findById(idOrder).get();
        if (order.getStatus() == OrderStatus.COMPLETED) {
            order.setStatus(OrderStatus.RETURNED);
            List<OrderLine> orderLines = order.getOrderLines();
            for (OrderLine ol : orderLines) {

                apiInventoryService.annulerOrder(ol.getIdProduct(), ol.getQuantity());
            }
            order.getBill().setBillType(BillType.RETURN);

        } else if (order.getStatus() == OrderStatus.PENDING) {
            log.info("order n'est completed");
        } else if (order.getStatus() == OrderStatus.CANCELED) {
            log.info("order est deja cancled");
        } else {
            log.info("order est dejà returned");

        }
        return orderRepository.save(order);
    }

    @Transactional
    public Order denyOrder(Long idOrder) {
        Order order = orderRepository.findById(idOrder).get();
        if (order.getStatus() == OrderStatus.PENDING) {
            order.setStatus(OrderStatus.CANCELED);
            List<OrderLine> orderLines = order.getOrderLines();
            for (OrderLine ol : orderLines) {

                apiInventoryService.annulerOrder(ol.getIdProduct(), ol.getQuantity());

            }

        } else if (order.getStatus() == OrderStatus.COMPLETED) {
            log.info("order est completed");

        } else if (order.getStatus() == OrderStatus.CANCELED) {
            log.info("order est dejà cancled");


        } else {
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


//    public Map<String, Integer> calculateClientProductCounts() {
//        Map<String, Integer> clientProductCounts = new HashMap<>();
//
//        List<Order> orders = orderRepository.findAll();
//        List<OrderLine> orderLines = orderLineRepository.findAll();
//        // Iterate through each order and order line and aggregate the product counts for each client
//        for (Order order : orders) {
//            String idClient = order.getIdClient();
//            if (order.getStatus() == OrderStatus.COMPLETED) {
//                String clientName = apiUserService.getClientNameById(idClient);
//                int totalProductCountForOrder = 0;
//                for (OrderLine orderLine : orderLines) {
//                    if (orderLine.getOrder().getIdOrder().equals(order.getIdOrder())) {
//                        totalProductCountForOrder += orderLine.getQuantity();
//                        System.out.println(orderLine.getQuantity());
//                    }
//                }
//
//                if (clientProductCounts.containsKey(clientName)) {
//                    clientProductCounts.put(clientName, clientProductCounts.get(clientName) + totalProductCountForOrder);
//                } else {
//                    clientProductCounts.put(clientName, totalProductCountForOrder);
//                }
//            }
//        }
//
//
//        return clientProductCounts;
//    }
//
//    public Map<String, Map<String, Integer>> displayClientProductCounts() {
//        List<OrderLine> orderLines = orderLineRepository.findAll();
//
//        Map<String, Map<String, Integer>> clientProductCounts = orderLines.stream()
//                .collect(Collectors.groupingBy(
//                        orderLine -> apiUserService.getClientNameById(orderLine.getOrder().getIdClient()),
//                        Collectors.groupingBy(
//                                orderLine -> apiInventoryService.getCategorieByIdProduct(orderLine.getIdProduct()).getNameCategorie(),
//                                Collectors.summingInt(OrderLine::getQuantity))));
//
//        //  if (orderLines.forEach(orderline->(orderline.getOrder().getStatus())==(OrderStatus.COMPLETED)){
//
//        for (Map.Entry<String, Map<String, Integer>> clientEntry : clientProductCounts.entrySet()) {
//            String clientId = clientEntry.getKey();
//            Map<String, Integer> productCounts = clientEntry.getValue();
//            System.out.println("Client " + clientId + " purchased the following products:");
//            for (Map.Entry<String, Integer> productEntry : productCounts.entrySet()) {
//                String productCategory = productEntry.getKey();
//                int totalProductCount = productEntry.getValue();
//                System.out.println("- " + productCategory + ": " + totalProductCount);
//            }
//        }
//        return clientProductCounts;
//    }


}

