package utils;

import interfaces.Readable;
import interfaces.Writeable;
import model.InventoryItem;

import java.io.*;

/**
 *
 */
public class InventoryReaderWriter implements Writeable, Readable {

    /**
     *
     */
    public InventoryReaderWriter() {

    }

    /**
     *
     * @param filename
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Override
    public InventoryItem read(String filename) throws IOException, ClassNotFoundException {
        return (InventoryItem) new ObjectInputStream(new FileInputStream(filename)).readObject();
    }

    /**
     *
     * @param item
     * @param filename
     * @throws IOException
     */
    @Override
    public void write(InventoryItem item, String filename) throws IOException {
        new ObjectOutputStream(new FileOutputStream(filename)).writeObject(item);
    }
}