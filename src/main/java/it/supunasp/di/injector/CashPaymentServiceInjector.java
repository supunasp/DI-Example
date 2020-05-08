package it.supunasp.di.injector;

import it.supunasp.di.consumer.ECommerceSite;
import it.supunasp.di.consumer.WebSite;
import it.supunasp.di.scope.ScopeType;
import it.supunasp.di.scope.ServiceScope;
import it.supunasp.di.service.CashPaymentServiceImpl;
import it.supunasp.di.service.PaymentService;

public class CashPaymentServiceInjector implements PaymentServiceInjector {

    PaymentService paymentService = null;

    public WebSite getWebSite() {

        PaymentService paymentService;

        Class<?> paymentServiceClass = CashPaymentServiceImpl.class;
        if (paymentServiceClass.isAnnotationPresent(ServiceScope.class)) {
            ScopeType scopeType = paymentServiceClass.getAnnotation(ServiceScope.class).value();

            switch (scopeType) {
                case PROTOTYPE:
                    paymentService = getPaymentServiceInstance();
                    break;
                case SINGLETON:
                    if (this.paymentService == null) {
                        this.paymentService = getPaymentServiceInstance();
                    }
                    paymentService = this.paymentService;
                    break;
                default:
                    throw new UnsupportedOperationException("Not implemented, yet");

            }
        } else {
            paymentService = getPaymentServiceInstance();
        }

        return new ECommerceSite(paymentService);
    }

    @Override
    public PaymentService getPaymentServiceInstance() {
        return new CashPaymentServiceImpl();
    }
}
