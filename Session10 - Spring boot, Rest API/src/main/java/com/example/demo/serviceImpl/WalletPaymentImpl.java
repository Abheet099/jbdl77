package com.example.demo.serviceImpl;

import org.springframework.stereotype.Service;

import com.example.demo.service.PaymentServiceInterf;

@Service("WalletPaymentImpl")
public class WalletPaymentImpl implements PaymentServiceInterf {
    @Override
    public void pay(String account, double amount) {

    }
}
