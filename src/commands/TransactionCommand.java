package commands;

import model.DebitCard;
import model.InventoryItem;
import model.PaypalWallet;
import model.User;

import java.util.LinkedList;
import java.util.Map;

public interface TransactionCommand {
    void execute(Map<InventoryItem, Integer> bucket, User customer, LinkedList<DebitCard> customerDebitCards, LinkedList<PaypalWallet> customerPaypalWallets, String paymentType, String IBAN);
}