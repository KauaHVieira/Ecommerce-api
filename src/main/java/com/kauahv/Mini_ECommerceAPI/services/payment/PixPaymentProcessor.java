package com.kauahv.Mini_ECommerceAPI.services.payment;

import com.kauahv.Mini_ECommerceAPI.domain.Order;
import com.kauahv.Mini_ECommerceAPI.domain.Payment;
import com.kauahv.Mini_ECommerceAPI.domain.PaymentWithPix;
import com.kauahv.Mini_ECommerceAPI.dto.PaymentRequestDTO;
import com.kauahv.Mini_ECommerceAPI.enums.PaymentStatus;
import com.kauahv.Mini_ECommerceAPI.enums.PaymentType;
import org.springframework.stereotype.Component;

@Component
public class PixPaymentProcessor implements PaymentProcessor{

    @Override
    public PaymentType getType() {return PaymentType.PIX;}

    @Override
    public Payment createPayment(PaymentRequestDTO dto, Order order) {
        PaymentWithPix payment = new PaymentWithPix();
        payment.setOrder(order);
        payment.setStatus(PaymentStatus.PENDING);

        return payment;
    }
}
