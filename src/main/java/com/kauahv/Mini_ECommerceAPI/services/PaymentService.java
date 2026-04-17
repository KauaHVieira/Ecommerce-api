package com.kauahv.Mini_ECommerceAPI.services;

import com.kauahv.Mini_ECommerceAPI.domain.Order;
import com.kauahv.Mini_ECommerceAPI.domain.Payment;
import com.kauahv.Mini_ECommerceAPI.dto.PaymentRequestDTO;
import com.kauahv.Mini_ECommerceAPI.dto.PaymentResponseDTO;
import com.kauahv.Mini_ECommerceAPI.enums.PaymentType;
import com.kauahv.Mini_ECommerceAPI.exception.ResourceNotFoundException;
import com.kauahv.Mini_ECommerceAPI.services.payment.PaymentProcessor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PaymentService {

    private final Map<PaymentType, PaymentProcessor> processors;

    public PaymentService(List<PaymentProcessor> processorList){
        this.processors = processorList.stream()
                .collect(Collectors.toMap(PaymentProcessor::getType, p -> p));
    }

    public Payment create(PaymentRequestDTO dto, Order order){
        PaymentProcessor processor = processors.get(dto.getPaymentType());

        if(processor == null){
            throw new ResourceNotFoundException("Unsupported payment type");
        }

        return processor.createPayment(dto, order);
    }

}
