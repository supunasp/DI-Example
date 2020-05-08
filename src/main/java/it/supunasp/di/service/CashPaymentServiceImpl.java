package it.supunasp.di.service;

import it.supunasp.di.model.PaymentRequest;
import it.supunasp.di.scope.ScopeType;
import it.supunasp.di.scope.ServiceScope;

import java.util.ArrayList;
import java.util.List;

@ServiceScope(ScopeType.SINGLETON)
public class CashPaymentServiceImpl implements PaymentService {

    private List<String> processedSources;

    @Override
    public boolean processPayment(PaymentRequest paymentRequest) {
        System.out.println("Cash payment done via " + paymentRequest.getSource() + " with amount=" + paymentRequest.getAmount());
        return true;
    }

    public List<String> getProcessedPayments() {
        if (processedSources==null)
        {
            processedSources = new ArrayList<>();
        }
        return processedSources;
    }

    public void updateProcessedPayments(String source) {
        if (processedSources==null)
        {
            processedSources = new ArrayList<>();
        }
        processedSources.add(source);
    }

}