package commands;

import model.InventoryItem;
import model.Store;
import utils.StoreReaderWriter;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Map;

/**
 *
 */
public class DisplayItemsCommand extends ParentCommand implements StoreCommand {

    /**
     *
     */
    public DisplayItemsCommand() {
        super("display_items");
    }

    public DisplayItemsCommand(String command) {
        super(command);
    }

    /**
     *
     * @param store
     */
    @Override
    public void execute(Store store) {
        LinkedList<InventoryItem> items = new LinkedList<>();
        for(Map.Entry<InventoryItem, Integer> stockItem : store.getStock().entrySet()) {
            System.out.printf("%s: %d articles in stock%n", stockItem.getKey(), stockItem.getValue());
        }

        StoreReaderWriter srw = new StoreReaderWriter();
        try {
            srw.write(store, "store.txt");
        } catch (IOException ex) {
            ex.fillInStackTrace();
        }
    }
}