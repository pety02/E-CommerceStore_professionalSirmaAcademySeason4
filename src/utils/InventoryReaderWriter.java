package utils;

import interfaces.Readable;
import interfaces.Writeable;
import model.AbstractItem;
import model.InventoryItem;

import java.io.*;

public class InventoryReaderWriter implements Writeable, Readable {

    public InventoryReaderWriter() {

    }

    @Override
    public InventoryItem read(String filename) throws IOException, ClassNotFoundException {
        return (InventoryItem) new ObjectInputStream(new FileInputStream(filename)).readObject();
    }

    @Override
    public void write(InventoryItem item, String filename) throws IOException {
        new ObjectOutputStream(new FileOutputStream(filename)).writeObject(item);
    }
}