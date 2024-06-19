package commands;

import model.InventoryItem;
import model.Store;
import java.util.Map;

/**
 *
 */
public class AddItem implements OrderCommand {
    private final InventoryItem item;
    private final int quantity;

    /**
     *
     * @param item
     * @param quantity
     */
    public AddItem(InventoryItem item, int quantity) {

        this.item = item;
        this.quantity = Math.max(quantity, 0);
    }

    /**
     *
     * @param bucket
     * @param store
     */
    @Override
    public void execute(Map<InventoryItem, Integer> bucket, Store store) {
        try {
            store.removeFromStock(this.item, this.quantity);
            bucket.put(this.item, this.quantity);
            System.out.printf("Item %s was successfully added in your bucket.%n", this.item);
        } catch (IllegalArgumentException ex) {
            ex.fillInStackTrace();
            System.out.printf("Item quantity not enough!%n");
        }
    }
}