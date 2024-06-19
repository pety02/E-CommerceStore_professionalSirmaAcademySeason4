package commands;

import model.DebitCard;
import model.InventoryItem;
import model.PaypalWallet;
import model.User;

import java.util.LinkedList;

public interface TransactionCommand {
    void execute(LinkedList<InventoryItem> bucket, User customer, LinkedList<DebitCard> customerDebitCards, LinkedList<PaypalWallet> customerPaypalWallets, String paymentType, String IBAN);
}