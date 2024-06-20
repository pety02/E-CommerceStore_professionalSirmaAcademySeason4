package commands;

import model.PaymentType;

/**
 *
 */
public class ChoosePaymentTypeCommand extends ParentCommand implements PaymentTypeCommand{

    /**
     *
     */
    public ChoosePaymentTypeCommand() {
        super("choose_payment_type");
    }


    public ChoosePaymentTypeCommand(String command) {
        super(command);
    }

    /**
     *
     * @param type
     * @return
     */
    @Override
    public PaymentType execute(String type) {
        return switch (type) {
            case "debit_ard" -> PaymentType.DEBIT_CARD;
            case "paypal" -> PaymentType.PAYPAL;
            default -> throw new IllegalArgumentException("Allowed payment types are debit_card or paypal.");
        };
    }
}