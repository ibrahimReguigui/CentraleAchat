package com.CentraleAchat.offerservice.controlles;

import com.CentraleAchat.offerservice.dto.BillDTO;
import com.CentraleAchat.offerservice.services.BillService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/bill")
public class BillController {

    BillService billService;

    @PostMapping("/createBill")
    @ResponseStatus(HttpStatus.CREATED)
    public BillDTO createBill(@Valid @RequestBody BillDTO billDto) {
        return billService.createBill(billDto);
    }

    @PutMapping("/updateBill")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public BillDTO updateBill(@Valid @RequestBody BillDTO billDto) {
        return billService.createBill(billDto);
    }

    @GetMapping("/retrieveAllBill")
    public List<BillDTO> retrieveAllBill() {
        return billService.retrieveAllBill();
    }

}
