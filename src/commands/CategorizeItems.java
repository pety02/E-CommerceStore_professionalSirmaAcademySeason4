package commands;

import model.Category;
import model.InventoryItem;
import model.Store;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 *
 */
public class CategorizeItems implements StoreCommand {
    /**
     *
     * @param store
     */
    @Override
    public void execute(Store store) {
        Map<Category, LinkedList<InventoryItem>> categories = new HashMap<>();
        LinkedList<InventoryItem> items = new LinkedList<>();
        for(Map.Entry<InventoryItem, Integer> stockItem : store.getStock().entrySet()) {
            items.add(stockItem.getKey());
        }
        for(InventoryItem currentItem : items) {
            if(categories.containsKey(currentItem.getCategory())) {
                categories.get(currentItem.getCategory()).add(currentItem);
            } else {
                categories.put(currentItem.getCategory(), new LinkedList<>());
                categories.get(currentItem.getCategory()).add(currentItem);
            }
        }

        for (Map.Entry<Category, LinkedList<InventoryItem>> entry : categories.entrySet()) {
            System.out.printf("%s:%n\t", entry.getKey().name().toUpperCase());
            for(InventoryItem currentItem : entry.getValue()) {
                System.out.printf("%s,%n\t", currentItem);
            }
            System.out.println();
        }
    }
}