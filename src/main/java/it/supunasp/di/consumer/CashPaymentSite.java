package it.supunasp.di.consumer;

import it.supunasp.di.annotate.AutoWired;
import it.supunasp.di.model.PaymentRequest;
import it.supunasp.di.service.CashPaymentServiceImpl;

import java.util.List;

public class CashPaymentSite implements WebSite {

    @AutoWired
    private CashPaymentServiceImpl service;

    @Override
    public boolean takePayment(PaymentRequest paymentRequest) {
        return this.service != null && this.service.confirmPayment(paymentRequest);
    }

    @Override
    public List<String> getProcessedPayments() {
        return service.getProcessedPayments();
    }

}
