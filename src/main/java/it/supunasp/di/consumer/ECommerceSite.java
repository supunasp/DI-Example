package it.supunasp.di.consumer;

import it.supunasp.di.model.PaymentRequest;
import it.supunasp.di.service.PaymentService;

import java.util.List;

public class ECommerceSite implements WebSite {

    private PaymentService service;

    public ECommerceSite(PaymentService svc) {
        this.service = svc;
    }

    @Override
    public boolean takePayment(PaymentRequest paymentRequest) {
        return this.service != null && this.service.confirmPayment(paymentRequest);
    }

    @Override
    public List<String> getProcessedPayments() {
        return service.getProcessedPayments();
    }

}
