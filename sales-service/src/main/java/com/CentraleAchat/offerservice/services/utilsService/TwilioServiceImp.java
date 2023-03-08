package com.CentraleAchat.offerservice.services.utilsService;

import com.twilio.Twilio;

import com.twilio.rest.api.v2010.account.Message;

import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class TwilioServiceImp  implements TwilioService{
    @Value("${twilio.account.sid}")
    private String accountSid;

    @Value("${twilio.auth.token}")
    private String authToken;

    @Value("${twilio.from.number}")
    private String fromNumber;

    @PostConstruct
    public void init() {
        Twilio.init(accountSid, authToken);
    }


    public void sendSms(String toNumber, String message) {
        Message.creator(
                        new PhoneNumber(toNumber),
                        new PhoneNumber(fromNumber),
                        message)
                .create();
    }

    }

