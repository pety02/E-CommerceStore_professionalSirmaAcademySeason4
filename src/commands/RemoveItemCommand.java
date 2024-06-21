package commands;

import model.InventoryItem;
import model.Store;
import utils.StoreReaderWriter;

import java.io.IOException;
import java.util.Map;

/**
 * Remove item command class
 */
public class RemoveItemCommand extends ParentCommand implements OrderCommand {
    private final int itemID;
    private final int quantity;

    /**
     * Constructs the remove item command object by a given command name.
     * @param command the given command name
     */
    public RemoveItemCommand(String command) {
        super(command);
        this.itemID = 0;
        this.quantity = 0;
        this.setType(CommandType.REMOVE_ITEM);
    }

    /**
     * Constructs remove item command object by given itemID and quantity of it.
     * @param itemID the given item's id
     * @param quantity the given items' quantity
     */
    public RemoveItemCommand(int itemID, int quantity) {
        super("remove_item");
        this.itemID = itemID;
        this.quantity = Math.max(quantity, 0);
        this.setType(CommandType.REMOVE_ITEM);
    }

    /**
     * Removes the given item's quantity from the customer's bucket and return it in the store's stock.
     * @param bucket the customer's bucket
     * @param store the store
     */
    @Override
    public void execute(Map<InventoryItem, Integer> bucket, Store store) {
        for(Map.Entry<InventoryItem, Integer> bucketItem : bucket.entrySet()) {
            if(bucketItem.getKey().getID() == this.itemID) {
                bucket.put(bucketItem.getKey(), this.quantity);
                store.addInStock(bucketItem.getKey(), this.quantity);
                System.out.printf("Item %s was successfully removed from your bucket.%n", bucketItem.getKey());
            }
        }

        // Writes the store's changes in a text file.
        StoreReaderWriter srw = new StoreReaderWriter();
        try {
            srw.write(store, "store.txt");
        } catch (IOException ex) {
            ex.fillInStackTrace();
        }
    }
}