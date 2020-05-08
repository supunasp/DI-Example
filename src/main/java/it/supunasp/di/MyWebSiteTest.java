package it.supunasp.di;

import it.supunasp.di.consumer.WebSite;
import it.supunasp.di.injector.CCPaymentServiceInjector;
import it.supunasp.di.injector.CashPaymentServiceInjector;
import it.supunasp.di.injector.PaymentServiceInjector;
import it.supunasp.di.model.PaymentRequest;

public class MyWebSiteTest {

    public static void main(String[] args) {

        PaymentRequest cashPaymentRequest = new PaymentRequest("110100000", 100.0);
        PaymentRequest cashPaymentRequestTwo = new PaymentRequest("110100001", 100.0);
        PaymentRequest creditCardPaymentRequest = new PaymentRequest("4104-0000-2222-2211", 100.0);
        PaymentRequest creditCardPaymentRequestTwo = new PaymentRequest("4104-0000-3333-2211", 100.0);
        PaymentServiceInjector injector;
        WebSite app;
        boolean success;

        /*
            cash payment based website [Singleton Scope]
         */

        injector = new CashPaymentServiceInjector();
        app = injector.getWebSite();
        success = app.takePayment(cashPaymentRequest);
        System.out.println("processed=" + success + " accounts : " + app.getProcessedPayments().size());

        app = injector.getWebSite();
        success = app.takePayment(cashPaymentRequestTwo);
        System.out.println("processed=" + success + " accounts : " + app.getProcessedPayments().size());

        /*
            credit card based website [Prototype Scope]
         */
        injector = new CCPaymentServiceInjector();
        app = injector.getWebSite();
        success = app.takePayment(creditCardPaymentRequest);
        System.out.println("processed=" + success + " credit cards : " + app.getProcessedPayments().size());

        app = injector.getWebSite();
        success = app.takePayment(creditCardPaymentRequestTwo);
        System.out.println("processed=" + success + " credit cards : " + app.getProcessedPayments().size());
    }

}
