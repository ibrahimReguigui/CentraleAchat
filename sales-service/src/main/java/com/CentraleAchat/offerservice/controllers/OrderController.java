package com.CentraleAchat.offerservice.controllers;

import com.CentraleAchat.offerservice.entities.Order;
import com.CentraleAchat.offerservice.services.APIUserService;
import com.CentraleAchat.offerservice.services.OrderService;
import com.CentraleAchat.offerservice.services.OrderServiceImp;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/order")
public class OrderController {

    OrderService orderService;
    APIUserService apiUserService;
    OrderServiceImp orderServiceImp;

      @PostMapping("/createOrder/{idClient}")
      @ResponseStatus(HttpStatus.CREATED)
      public Order createOrder(@RequestBody Order Order, @PathVariable String idClient) {
          return orderService.sendOrder(Order,idClient);
      }

    @PutMapping("/updateOrder")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Order updateOrder(@Valid @RequestBody Order order) {
        return orderService.updateOrder(order);
    }

    @GetMapping("/retrieveAllOrder")
    @ResponseStatus(HttpStatus.OK)
    public List<Order> retrieveAllOrder() {
        return orderService.retrieveAllOrder();
    }

    @PostMapping("/confirmerOrder/{idOrder}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Order confirmerOrder(@PathVariable Long idOrder){
        return orderService.confirmerOrder(idOrder);
    }

    @PostMapping("/denyOrder/{idOrder}")
    public Order denyOrder(@PathVariable Long idOrder) {
        return    orderService.denyOrder(idOrder);
    }
    @PostMapping("/retournerOrder/{idOrder}")
    public Order retournerOrder(@PathVariable Long idOrder) {
          return orderService.retournerOrder(idOrder);
    }
//    @GetMapping("/get")
//    public Map<String, Integer> calculateClientProductCounts() {
//       return    orderServiceImp.calculateClientProductCounts();
//    }
//
//    @GetMapping("/getByCategory")
//    public Map<String, Map<String, Integer>> displayClientProductCounts(){
//          return orderServiceImp.displayClientProductCounts();
//    }
}
