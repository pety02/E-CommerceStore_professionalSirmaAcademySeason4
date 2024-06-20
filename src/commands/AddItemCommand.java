package commands;

import model.InventoryItem;
import model.Store;
import utils.StoreReaderWriter;

import java.io.IOException;
import java.util.Map;

/**
 *
 */
public class AddItemCommand extends ParentCommand implements OrderCommand {
    private InventoryItem item;
    private int quantity;

    /**
     *
     * @param command
     */
    public AddItemCommand(String command) {
        super(command);
    }

    /**
     *
     * @param item
     * @param quantity
     */
    public AddItemCommand(InventoryItem item, int quantity) {
        super("add_item");
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
        StoreReaderWriter srw = new StoreReaderWriter();
        try {
            srw.write(store, "store.txt");
        } catch (IOException ex) {
            ex.fillInStackTrace();
        }
    }
}