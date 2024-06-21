package commands;

import model.Category;
import model.InventoryItem;
import model.Store;
import utils.StoreReaderWriter;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Categorize items command class
 */
public class CategorizeItemsCommand extends ParentCommand implements StoreCommand {

    /**
     * Constructs categorize command object by default
     */
    public CategorizeItemsCommand() {
        super("categorize_items");
    }

    /**
     * Constructs categorize items command object by command name.
     * @param command the command name
     */
    public CategorizeItemsCommand(String command) {
        super(command);
    }

    /**
     * Categorize all store items in stock.
     * @param store the store
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
            int itemsCounter = 0;
            for(InventoryItem currentItem : entry.getValue()) {
                if(itemsCounter  == categories.size()) {
                    System.out.printf("%s%n\t", currentItem);
                    break;
                }
                System.out.printf("%s,%n\t", currentItem);
                ++itemsCounter;
            }
            System.out.println();
        }
    }
}