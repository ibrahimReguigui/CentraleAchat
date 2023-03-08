package com.CentraleAchat.offerservice.controllers;

import com.CentraleAchat.offerservice.entities.Bill;
import com.CentraleAchat.offerservice.entities.BillStatus;
import com.CentraleAchat.offerservice.repositories.BillRepository;
import com.CentraleAchat.offerservice.services.entities.BillService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import javax.annotation.security.RolesAllowed;

@RestController
@AllArgsConstructor
@RequestMapping("/bill")
public class BillController {


    @Value("${stripe.secretKey}")
    private String stripeSecretKey;
    private BillService billService;
    private BillRepository billRepository;

    @RolesAllowed({"CLIENT"})
    @PostMapping("/paiement")
    public Bill createPayment(@RequestParam("idBill") Long idBill) {
        Stripe.apiKey = stripeSecretKey;

        Bill bill=billRepository.findById(idBill).get();
        PaymentIntentCreateParams params = new PaymentIntentCreateParams.Builder()
                .setAmount((long) (bill.getTotalTTC() * 100))
                .setCurrency("eur")
                .build();

        try {
            PaymentIntent paymentIntent = PaymentIntent.create(params);

            // Mettre à jour le statut de la facture en "payée"
            bill.setBillStatus(BillStatus.PAYEE);
            billRepository.save(bill);

            // return "facture payeé ";
            return bill;
        } catch (StripeException e) {
            // return"Erreur lors de la création du paiement  ";
            return bill;
        }
    }
}
