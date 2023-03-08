package com.CentraleAchat.offerservice.services.utilsService;

public interface TwilioService {
    void sendSms(String toNumber, String message);
}
