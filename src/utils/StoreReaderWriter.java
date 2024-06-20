package utils;

import interfaces.Readable;
import interfaces.Writeable;
import model.Store;

import java.io.*;

/**
 *
 */
public class StoreReaderWriter implements Readable<Store>, Writeable<Store> {

    /**
     *
     */
    public StoreReaderWriter() {
    }

    /**
     *
     * @param filename
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Override
    public Store read(String filename) throws IOException, ClassNotFoundException {
        return (Store) new ObjectInputStream(new FileInputStream(filename)).readObject();
    }

    /**
     *
     * @param item
     * @param filename
     * @throws IOException
     */
    @Override
    public void write(Store item, String filename) throws IOException {
        new ObjectOutputStream(new FileOutputStream(filename)).writeObject(item);
    }
}