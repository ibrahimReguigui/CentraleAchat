package com.CentraleAchat.offerservice.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("USER-SERVICE")
public interface APIUserService {

    @GetMapping("user/client/{id}")
    String getClientNameById(@PathVariable Long id);

    @GetMapping("user/getNumeroClient/{idClient}")
    String getNumeroClient(@PathVariable Long idClient);

}
