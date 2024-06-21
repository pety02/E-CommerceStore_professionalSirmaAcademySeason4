package commands;

import model.InventoryItem;
import model.Store;
import utils.StoreReaderWriter;

import java.io.IOException;
import java.util.Map;

/**
 * Add item command class.
 */
public class AddItemCommand extends ParentCommand implements OrderCommand {

    private InventoryItem item;
    private int quantity;

    /**
     * Constructs add item command object from a string.
     * @param command the name of the command
     */
    public AddItemCommand(String command) {
        super(command);
        this.setItem(null);
        this.setQuantity(0);
    }

    /**
     * Constructs add item command by given item and given quantity.
     * @param item given item
     * @param quantity given quantity
     */
    public AddItemCommand(InventoryItem item, int quantity) {
        this.setCommand("add_item");
        this.setItem(item);
        this.setQuantity(quantity);
        this.setType(CommandType.ADD_ITEM);
    }

    /**
     * Item Getter
     * @return the item.
     */
    public InventoryItem getItem() {
        return item;
    }

    /**
     * Sets the item given item's value
     * @param item given item
     */
    public void setItem(InventoryItem item) {
        this.item = item;
    }

    /**
     * Quantity Getter
     * @return the quantity.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity given quantity value.
     * @param quantity given quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = Math.max(quantity, 0);
    }

    /**
     * Add items to customer's bucket and remove them from the store's stock
     * @param bucket the customer's bucket
     * @param store the store
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

        // Write down the shop's new changes in a text file.
        StoreReaderWriter srw = new StoreReaderWriter();
        try {
            srw.write(store, "store.txt");
        } catch (IOException ex) {
            ex.fillInStackTrace();
        }
    }
}