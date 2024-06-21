package commands;

import model.*;
import utils.PaymentReaderWriter;
import utils.UserReaderWriter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Map;

/**
 * Place order command class
 */
public class PlaceOrderCommand extends ParentCommand implements TransactionCommand {

    /**
     * Constructs place order command object by default.
     */
    public PlaceOrderCommand () {
        super("place_order");
        this.setType(CommandType.PLACE_ORDER);
    }

    /**
     * Constructs place order command object by given command name.
     * @param command the given command name
     */
    public PlaceOrderCommand(String command) {
        super(command);
    }

    /**
     * Places order and invoke PaymentProcessor object's makeTransaction method by given IBAN.
     * @param bucket the customer's bucket
     * @param customer the customer
     * @param customerDebitCards the customer debit cards
     * @param customerPaypalWallets the customer paypal wallets
     * @param paymentType the payment type, preferred by the customer
     * @param IBAN the IBAN which will be used for paying the order's total price
     */
    @Override
    public void execute(Map<InventoryItem, Integer> bucket, User customer, LinkedList<DebitCard> customerDebitCards,
                        LinkedList<PaypalWallet> customerPaypalWallets, String paymentType, String IBAN) {
        if(bucket.isEmpty()) {
            System.out.println("Your bucket is empty so you cannot place order.");
            return;
        }
        Order customerOrder = new Order(bucket);
        PaymentProcessor orderPayment = new PaymentProcessor(LocalDateTime.now(),
                customer, customerDebitCards, customerPaypalWallets, customerOrder,
                new ChoosePaymentTypeCommand().execute(paymentType));
        orderPayment.makeTransaction(IBAN);

        // Write the user's changes in a text file.
        UserReaderWriter urw = new UserReaderWriter();
        try {
            urw.write(customer, "customers.txt");
        } catch (IOException ex) {
            ex.fillInStackTrace();
        }

        // Write the payment's changes in a text file.
        PaymentReaderWriter prw = new PaymentReaderWriter();
        try {
            prw.write(orderPayment, "payments.txt");
        } catch (IOException ex) {
            ex.fillInStackTrace();
        }
    }
}