package commands;

import model.InventoryItem;
import model.Store;

import java.util.Map;

/**
 *
 */
public class RemoveItem implements OrderCommand {
    private final int itemID;
    private final int quantity;

    /**
     *
     * @param itemID
     * @param quantity
     */
    public RemoveItem(int itemID, int quantity) {
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
    }
}