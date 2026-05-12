package com.kauahv.Mini_ECommerceAPI.payment;

import com.kauahv.Mini_ECommerceAPI.order.Order;
import com.kauahv.Mini_ECommerceAPI.payment.dto.PaymentRequestDTO;
import com.kauahv.Mini_ECommerceAPI.payment.enums.PaymentStatus;
import com.kauahv.Mini_ECommerceAPI.payment.enums.PaymentType;
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
