package com.kauahv.Mini_ECommerceAPI.payment;

import com.kauahv.Mini_ECommerceAPI.order.Order;
import com.kauahv.Mini_ECommerceAPI.payment.dto.PaymentRequestDTO;
import com.kauahv.Mini_ECommerceAPI.payment.enums.PaymentType;

public interface PaymentProcessor {

    PaymentType getType();
    Payment createPayment(PaymentRequestDTO dto, Order order);
}
