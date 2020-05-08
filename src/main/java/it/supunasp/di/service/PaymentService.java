package it.supunasp.di.service;

import it.supunasp.di.model.PaymentRequest;

import java.util.List;

public interface PaymentService {

    default boolean confirmPayment(PaymentRequest paymentRequest) {
        boolean success = false;
        if (paymentRequest != null) {
            success = processPayment(paymentRequest);
            if (success) {
                updateProcessedPayments(paymentRequest.getSource());
            }
        }
        return success;
    }

    boolean processPayment(PaymentRequest paymentRequest);

    List<String> getProcessedPayments();

    void updateProcessedPayments(String source);
}
