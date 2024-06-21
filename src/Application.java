import commands.*;
import model.*;
import utils.CommandLineParser;
import utils.CommandValidator;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

/**
 * Application run class
 */
public class Application {
    /**
     * Fills a given store with all its items.
     * @param store the given store
     */
    private static void fillStore(Store store) {
        store.addInStock(new FragileItem(20, 100.25, 25.00), 25);
        store.addInStock(new ElectronicsItem(100, 10.50, 1.00, ElectronicsType.MOUSE), 100);
        store.addInStock(new GroceryItem(5, 5.85,
                LocalDateTime.of(2024, Month.NOVEMBER,15, 12, 30)), 5);
    }

    /**
     * Initializes a menu.
     */
    private static void initMenu() {
        System.out.println("MENU:");
        System.out.println("display_items");
        System.out.println("add_item *<category> *<quantity> *<price> <weight> <electronics_type> <expiration_date>");
        System.out.println("remove_item *<ID>");
        System.out.println("categorize_items");
        System.out.println("place_order");
        System.out.println("choose_payment_type *<payment_type>");
    }

    /**
     * Executes a given command.
     * @param cmd the given command
     * @param customerBucket the given customer bucket
     * @param store the given store
     * @param customer the given customer
     * @param customerCards the given customer's debit cards list
     * @param customerWallets the given customer's paypal wallets list
     * @param paymentType the given payment type
     * @param paymentIBAN the given payment IBAN
     * @throws IllegalArgumentException when the command is invalid or unknown.
     */
    private static void executeCommand(ParentCommand cmd, Map<InventoryItem, Integer> customerBucket,
                                       Store store, User customer, LinkedList<DebitCard> customerCards,
                                       LinkedList<PaypalWallet> customerWallets, String paymentType,
                                       String paymentIBAN) throws IllegalArgumentException {
        if(paymentType.isEmpty()) {
            paymentType = "debit_card";
        }
        switch (cmd.getType()) {
            case ADD_ITEM -> {
                ((AddItemCommand) cmd).execute(customerBucket, store);
                System.out.printf("%d items in bucket %n", customerBucket.size());
                for(Map.Entry<InventoryItem, Integer> item : customerBucket.entrySet()) {
                    System.out.println(item);
                }
            }
            case REMOVE_ITEM -> {
                ((RemoveItemCommand) cmd).execute(customerBucket, store);
                System.out.printf("%d items in bucket %n", customerBucket.size());
                for(Map.Entry<InventoryItem, Integer> item : customerBucket.entrySet()) {
                    System.out.println(item);
                }
            }
            case DISPLAY_ITEMS -> {
                ((DisplayItemsCommand) cmd).execute(store);
            }
            case CATEGORIZE_ITEMS -> {
                ((CategorizeItemsCommand) cmd).execute(store);
            }
            case PLACE_ORDER -> {
                ((PlaceOrderCommand) cmd).execute(customerBucket, customer, customerCards, customerWallets, paymentType, paymentIBAN);
            }
            case CHOOSE_PAYMENT_TYPE -> {
                ((ChoosePaymentTypeCommand) cmd).execute(paymentType);
            }
            default -> {
                throw new IllegalArgumentException("Invalid command!");
            }
        };
    }

    /**
     * Application start point
     * @param args the arguments of the main() method
     */
    public static void main(String[] args) {
        Map<InventoryItem, Integer> stock = new HashMap<>();
        Store myStore = new Store("MyStore", stock);
        fillStore(myStore);

        User customer = new User("Petya");
        LinkedList<DebitCard> customerDebitCards = new LinkedList<>();
        customerDebitCards.add(new DebitCard(1000.00, customer));
        LinkedList<PaypalWallet> customerPaypalWallets = new LinkedList<>();
        customerPaypalWallets.add(new PaypalWallet("Petya Paypal wallet", 500.25, customer));
        System.out.println("Welcome to the E-commerce Store Console Application!");
        String cmdLine = "";
        Scanner sc = new Scanner(System.in);

        initMenu();
        do {
            do {
                if(cmdLine.equals("END")) {
                    break;
                }
                System.out.print(">");
                cmdLine = sc.nextLine();
            } while (!CommandValidator.validate(cmdLine));
            if(cmdLine.equals("END")) {
                break;
            }
            CommandLineParser cmdParser = new CommandLineParser();
            cmdParser.parseCommand(cmdLine);
            Map<InventoryItem, Integer> customerBucket = new HashMap<>();
            ParentCommand executableCommand = cmdParser.getParsedCommand();
            try {
                String paymentType = "";
                executeCommand(executableCommand, customerBucket, myStore, customer,
                        customerDebitCards, customerPaypalWallets, paymentType,
                        customerDebitCards.getFirst().getIBAN());
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        } while (true);
        System.out.println("Thank you for shopping from E-Commerce Store! Goodbye...");
        System.exit(0);
    }
}