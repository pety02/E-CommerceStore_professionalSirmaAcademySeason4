package commands;

import model.*;

import java.time.LocalDateTime;
import java.util.LinkedList;

/**
 *
 */
public class PlaceOrder implements TransactionCommand {
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
    public void execute(LinkedList<InventoryItem> bucket, User customer, LinkedList<DebitCard> customerDebitCards, LinkedList<PaypalWallet> customerPaypalWallets, String paymentType, String IBAN) {
        Order customerOrder = new Order(bucket);
        PaymentProcessor orderPayment = new PaymentProcessor(LocalDateTime.now(), customer, customerDebitCards, customerPaypalWallets, customerOrder, new ChoosePaymentType().execute(paymentType));
        orderPayment.makeTransaction(IBAN);
        System.out.printf("Successful payment with ID: %d! The payment was made by customer with nickname: %s%n",
                orderPayment.getID(), customer.getNickname());
    }
}