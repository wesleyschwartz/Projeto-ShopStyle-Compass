package com.shopstyle.checkout.service;

import com.shopstyle.checkout.model.Payment;
import com.shopstyle.checkout.model.DTO.PaymentDTO;
import com.shopstyle.checkout.respositories.PaymentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    public Payment create(PaymentDTO paymentDTO) {
        return paymentRepository.save(new ModelMapper().map(paymentDTO, Payment.class));
    }

    public PaymentDTO findById(long id) {
        Payment payment = paymentRepository.findById(id).get();
        return new ModelMapper().map(payment, PaymentDTO.class);
    }

    public Payment update(PaymentDTO paymentDTO) {
        findById(paymentDTO.getPayment_id());
        Payment save = new ModelMapper().map(paymentDTO, Payment.class);
        return paymentRepository.save(save);
    }

    public void deleteById(long id) {
        findById(id);
        paymentRepository.deleteById(id);
    }

    public List<PaymentDTO> findAll() {
        List<PaymentDTO> paymentDTOS = new ArrayList<>();
        paymentRepository.findAll().forEach(payment -> {
            paymentDTOS.add(new ModelMapper().map(payment, PaymentDTO.class));
        });
        return paymentDTOS;
    }
}
