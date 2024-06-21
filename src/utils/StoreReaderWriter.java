package utils;

import interfaces.Readable;
import interfaces.Writeable;
import model.Store;

import java.io.*;

/**
 * Store reader writer class
 */
public class StoreReaderWriter implements Readable<Store>, Writeable<Store> {

    /**
     * Constructs the store reader writer object by default.
     */
    public StoreReaderWriter() {
    }

    /**
     * Reads from a file with a definite name.
     * @param filename the given filename.
     * @return the read object.
     * @throws IOException when cannot read from the file.
     * @throws ClassNotFoundException  when cannot parse to object of this class.
     */
    @Override
    public Store read(String filename) throws IOException, ClassNotFoundException {
        return (Store) new ObjectInputStream(new FileInputStream(filename)).readObject();
    }

    /**
     * Writes the item in a file with a definite name.
     * @param item the given item
     * @param filename the given filename
     * @throws IOException when cannot write in a file.
     */
    @Override
    public void write(Store item, String filename) throws IOException {
        new ObjectOutputStream(new FileOutputStream(filename)).writeObject(item);
    }
}