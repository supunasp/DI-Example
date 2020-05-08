package it.supunasp.di.injector;


import it.supunasp.di.consumer.WebSite;
import it.supunasp.di.service.PaymentService;

public interface PaymentServiceInjector {

    WebSite getWebSite();

    PaymentService getPaymentServiceInstance();
}
