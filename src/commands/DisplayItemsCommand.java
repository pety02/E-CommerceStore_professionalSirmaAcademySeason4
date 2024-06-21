package commands;

import model.InventoryItem;
import model.Store;
import utils.StoreReaderWriter;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Map;

/**
 * Display items command class.
 */
public class DisplayItemsCommand extends ParentCommand implements StoreCommand {

    /**
     * Constructs display items command object by default.
     */
    public DisplayItemsCommand() {
        super("display_items");
    }

    /**
     * Constructs display items command object by given command name.
     * @param command the given command name.
     */
    public DisplayItemsCommand(String command) {
        super(command);
        this.setType(CommandType.DISPLAY_ITEMS);
    }

    /**
     * Displays full description of all store stock's items.
     * @param store the store.
     */
    @Override
    public void execute(Store store) {
        LinkedList<InventoryItem> items = new LinkedList<>();
        for(Map.Entry<InventoryItem, Integer> stockItem : store.getStock().entrySet()) {
            System.out.printf("%s: %d articles in stock%n", stockItem.getKey(), stockItem.getValue());
        }
    }
}