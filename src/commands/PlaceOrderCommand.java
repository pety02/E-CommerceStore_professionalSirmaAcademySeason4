package commands;

import model.*;
import utils.PaymentReaderWriter;
import utils.UserReaderWriter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Map;

/**
 *
 */
public class PlaceOrderCommand extends ParentCommand implements TransactionCommand {

    /**
     *
     */
    public PlaceOrderCommand () {
        super("place_order");
    }

    /**
     *
     * @param command
     */
    public PlaceOrderCommand(String command) {
        super(command);
    }

    /**
     *
     * @param bucket
     * @param customer
     * @param customerDebitCards
     * @param customerPaypalWallets
     * @param paymentType
     * @param IBAN
     */
    @Override
    public void execute(Map<InventoryItem, Integer> bucket, User customer, LinkedList<DebitCard> customerDebitCards,
                        LinkedList<PaypalWallet> customerPaypalWallets, String paymentType, String IBAN) {
        Order customerOrder = new Order(bucket);
        PaymentProcessor orderPayment = new PaymentProcessor(LocalDateTime.now(), customer, customerDebitCards, customerPaypalWallets, customerOrder, new ChoosePaymentTypeCommand().execute(paymentType));
        orderPayment.makeTransaction(IBAN);

        UserReaderWriter urw = new UserReaderWriter();
        try {
            urw.write(customer, "customers.txt");
        } catch (IOException ex) {
            ex.fillInStackTrace();
        }
        PaymentReaderWriter prw = new PaymentReaderWriter();
        try {
            prw.write(orderPayment, "payments.txt");
        } catch (IOException ex) {
            ex.fillInStackTrace();
        }
    }
}