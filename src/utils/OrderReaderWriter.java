package utils;

import interfaces.Readable;
import interfaces.Writeable;
import model.Order;

import java.io.*;

/**
 * Order reader writer class
 */
public class OrderReaderWriter implements Readable<Order>, Writeable<Order> {

    /**
     * Constructs the order reader writer object by default.
     */
    public OrderReaderWriter() {
    }

    /**
     * Reads from a file with a definite name.
     * @param filename the given filename.
     * @return the read object.
     * @throws IOException when cannot read from the file.
     * @throws ClassNotFoundException  when cannot parse to object of this class.
     */
    @Override
    public Order read(String filename) throws IOException, ClassNotFoundException {
        return (Order) new ObjectInputStream(new FileInputStream(filename)).readObject();
    }

    /**
     * Writes the item in a file with a definite name.
     * @param item the given item
     * @param filename the given filename
     * @throws IOException when cannot write in a file.
     */
    @Override
    public void write(Order item, String filename) throws IOException {
        new ObjectOutputStream(new FileOutputStream(filename)).writeObject(item);
    }
}