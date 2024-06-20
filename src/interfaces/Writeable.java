package interfaces;

import model.InventoryItem;

import java.io.IOException;

public interface Writeable<T> {
    void write(T item, String filename) throws IOException;
}