package it.supunasp.di;

import it.supunasp.di.consumer.CCPaymentSite;
import it.supunasp.di.consumer.CashPaymentSite;
import it.supunasp.di.consumer.HybridPaymentSite;
import it.supunasp.di.consumer.WebSite;
import it.supunasp.di.injector.AutoWiredInjector;
import it.supunasp.di.model.PaymentRequest;


public class MyWebSiteTest {

    public static void main(String[] args) throws Exception {

        AutoWiredInjector autoWiredInjector = new AutoWiredInjector();


        PaymentRequest cashPaymentRequest = new PaymentRequest("110100000", 100.0);
        PaymentRequest cashPaymentRequestTwo = new PaymentRequest("110100001", 100.0);
        PaymentRequest creditCardPaymentRequest = new PaymentRequest("4104-0000-2222-2211", 100.0);
        PaymentRequest creditCardPaymentRequestTwo = new PaymentRequest("4104-0000-3333-2211", 100.0);

        WebSite paymentSite;
        boolean success;

        /*
            cash payment based website [Singleton Scope]
         */

        paymentSite = new CashPaymentSite();
        autoWiredInjector.injectBeans(paymentSite);


        success = paymentSite.takePayment(cashPaymentRequest);
        System.out.println("[Cash] processed=" + success + " accounts : " + paymentSite.getProcessedPayments().size());

        paymentSite = new CashPaymentSite();
        autoWiredInjector.injectBeans(paymentSite);

        success = paymentSite.takePayment(cashPaymentRequestTwo);
        System.out.println("[Cash]processed=" + success + " accounts : " + paymentSite.getProcessedPayments().size());

        /*
            credit card based website [Prototype Scope]
         */


        paymentSite = new CCPaymentSite();
        autoWiredInjector.injectBeans(paymentSite);

        success = paymentSite.takePayment(creditCardPaymentRequest);
        System.out.println("[CC] processed=" + success + " credit cards : " + paymentSite.getProcessedPayments().size());

        paymentSite = new CCPaymentSite();
        autoWiredInjector.injectBeans(paymentSite);

        success = paymentSite.takePayment(creditCardPaymentRequestTwo);
        System.out.println("[CC] processed=" + success + " credit cards : " + paymentSite.getProcessedPayments().size());


        /*
            credit card and cash based website [Prototype Scope and singleton]
         */

        HybridPaymentSite hybridPaymentSite = new HybridPaymentSite();
        autoWiredInjector.injectBeans(hybridPaymentSite);


        success = hybridPaymentSite.takeCashPayment(cashPaymentRequest);
        System.out.println("[Hybrid] processed=" + success + " accounts : " + hybridPaymentSite.getCashProcessedPayments().size());

        hybridPaymentSite = new HybridPaymentSite();
        autoWiredInjector.injectBeans(hybridPaymentSite);

        success = hybridPaymentSite.takeCashPayment(cashPaymentRequestTwo);
        System.out.println("[Hybrid] processed=" + success + " accounts : " + hybridPaymentSite.getCashProcessedPayments().size());


        hybridPaymentSite = new HybridPaymentSite();
        autoWiredInjector.injectBeans(hybridPaymentSite);

        success = hybridPaymentSite.takePayment(creditCardPaymentRequest);
        System.out.println("[Hybrid] processed=" + success + " credit cards : " + hybridPaymentSite.getProcessedPayments().size());

        hybridPaymentSite = new HybridPaymentSite();
        autoWiredInjector.injectBeans(hybridPaymentSite);

        success = hybridPaymentSite.takePayment(creditCardPaymentRequestTwo);
        System.out.println("[Hybrid] processed=" + success + " credit cards : " + hybridPaymentSite.getProcessedPayments().size());
    }
}
