package it.supunasp.di.consumer;

import it.supunasp.di.model.PaymentRequest;

import java.util.List;

public interface WebSite {

    boolean takePayment(PaymentRequest paymentRequest);

    List<String> getProcessedPayments();
}
