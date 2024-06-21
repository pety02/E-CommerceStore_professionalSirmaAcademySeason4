package commands;

import model.PaymentType;

/**
 * Choose payment type command class
 */
public class ChoosePaymentTypeCommand extends ParentCommand implements PaymentTypeCommand{

    /**
     * Constructs choose payment type object by default.
     */
    public ChoosePaymentTypeCommand() {
        super("choose_payment_type");
    }


    /**
     * Construct choose payment type command by given command name.
     * @param command the command name
     */
    public ChoosePaymentTypeCommand(String command) {
        super(command);
    }

    /**
     * Allow the customer to choose his/her preferred payment type between two options - debit_card or paypal.
     * @param type preferred payment type as string.
     * @return the preferred payment type as an enum option.
     */
    @Override
    public PaymentType execute(String type) {
        return switch (type) {
            case "debit_card" -> PaymentType.DEBIT_CARD;
            case "paypal" -> PaymentType.PAYPAL;
            default -> throw new IllegalArgumentException("Allowed payment types are debit_card or paypal.");
        };
    }
}