package com.example.demo.serviceImpl;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.example.demo.service.PaymentServiceInterf;

@Service("CardPaymentImpl")
@Primary
public class CardPaymentImpl implements PaymentServiceInterf {
    @Override
    public void pay(String account, double amount) {

    }
}
