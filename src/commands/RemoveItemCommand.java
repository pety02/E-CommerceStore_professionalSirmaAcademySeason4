package commands;

import model.InventoryItem;
import model.Store;
import utils.StoreReaderWriter;

import java.io.IOException;
import java.util.Map;

/**
 *
 */
public class RemoveItemCommand extends ParentCommand implements OrderCommand {
    private final int itemID;
    private final int quantity;

    /**
     *
     * @param command
     */
    public RemoveItemCommand(String command) {
        super(command);
        this.itemID = 0;
        this.quantity = 0;
    }

    /**
     *
     * @param itemID
     * @param quantity
     */
    public RemoveItemCommand(int itemID, int quantity) {
        super("remove_item");
        this.itemID = itemID;
        this.quantity = Math.max(quantity, 0);
    }

    /**
     *
     * @param bucket
     * @param store
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

        StoreReaderWriter srw = new StoreReaderWriter();
        try {
            srw.write(store, "store.txt");
        } catch (IOException ex) {
            ex.fillInStackTrace();
        }
    }
}