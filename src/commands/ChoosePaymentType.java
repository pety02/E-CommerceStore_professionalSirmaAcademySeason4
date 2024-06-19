package commands;

import model.PaymentType;

/**
 *
 */
public class ChoosePaymentType implements PaymentTypeCommand{
    /**
     *
     * @param type
     * @return
     */
    @Override
    public PaymentType execute(String type) {
        return switch (type) {
            case "Debit card" -> PaymentType.DEBIT_CARD;
            case "Paypal" -> PaymentType.PAYPAL;
            default -> throw new IllegalArgumentException("Allowed payment types are Debit card or Paypal.");
        };
    }
}