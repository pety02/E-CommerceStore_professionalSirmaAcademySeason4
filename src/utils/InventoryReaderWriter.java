package utils;

import interfaces.Readable;
import interfaces.Writeable;
import model.InventoryItem;

import java.io.*;

/**
 * Inventory reader writer class
 */
public class InventoryReaderWriter implements Writeable<InventoryItem>, Readable<InventoryItem> {

    /**
     * Constructs the inventory reader writer object by default.
     */
    public InventoryReaderWriter() {

    }

    /**
     * Reads from a file with a definite name.
     * @param filename the given filename.
     * @return the read object.
     * @throws IOException when cannot read from the file.
     * @throws ClassNotFoundException  when cannot parse to object of this class.
     */
    @Override
    public InventoryItem read(String filename) throws IOException, ClassNotFoundException {
        return (InventoryItem) new ObjectInputStream(new FileInputStream(filename)).readObject();
    }

    /**
     * Writes the item in a file with a definite name.
     * @param item the given item
     * @param filename the given filename
     * @throws IOException when cannot write in a file.
     */
    @Override
    public void write(InventoryItem item, String filename) throws IOException {
        new ObjectOutputStream(new FileOutputStream(filename)).writeObject(item);
    }
}