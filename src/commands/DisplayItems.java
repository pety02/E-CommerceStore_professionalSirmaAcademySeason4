package commands;

import model.InventoryItem;
import model.Store;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Map;

/**
 *
 */
public class DisplayItems implements StoreCommand {
    /**
     *
     */
    public DisplayItems() {

    }

    /**
     *
     * @param store
     */
    @Override
    public void execute(Store store) {
        LinkedList<InventoryItem> items = new LinkedList<>();
        for(Map.Entry<InventoryItem, Integer> stockItem : store.getStock().entrySet()) {
            items.add(stockItem.getKey());
        }
        System.out.println(Arrays.toString(items.toArray()));
    }
}