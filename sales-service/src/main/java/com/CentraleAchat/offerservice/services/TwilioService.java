package com.CentraleAchat.offerservice.services;

public interface TwilioService {
    void sendSms(String toNumber, String message);
}
