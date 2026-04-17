package com.kauahv.Mini_ECommerceAPI.services.payment;

import com.kauahv.Mini_ECommerceAPI.domain.Order;
import com.kauahv.Mini_ECommerceAPI.domain.Payment;
import com.kauahv.Mini_ECommerceAPI.dto.PaymentRequestDTO;
import com.kauahv.Mini_ECommerceAPI.enums.PaymentType;

public interface PaymentProcessor {

    PaymentType getType();
    Payment createPayment(PaymentRequestDTO dto, Order order);
}
