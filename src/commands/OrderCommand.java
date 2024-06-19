package commands;

import model.InventoryItem;
import model.Store;

import java.util.Map;

public interface OrderCommand {
    void execute(Map<InventoryItem, Integer> bucket, Store store);
}