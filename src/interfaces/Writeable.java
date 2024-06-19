package interfaces;

import model.InventoryItem;

import java.io.IOException;

public interface Writeable {
    void write(InventoryItem item, String filename) throws IOException;
}