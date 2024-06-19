package interfaces;

import model.InventoryItem;

import java.io.IOException;

public interface Readable {
    InventoryItem read(String filename) throws IOException, ClassNotFoundException;
}
