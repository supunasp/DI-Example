package it.supunasp.di.model;

public class PaymentRequest {

    private String source;
    private double amount;

    public PaymentRequest(String source, double amount) {
        this.source = source;
        this.amount = amount;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
