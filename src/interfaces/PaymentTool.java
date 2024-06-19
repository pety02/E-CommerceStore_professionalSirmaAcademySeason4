package interfaces;

public interface PaymentTool {
    void pay(double total) throws IllegalArgumentException;
}