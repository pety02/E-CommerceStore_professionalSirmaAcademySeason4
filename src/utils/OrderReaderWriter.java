package utils;

import interfaces.Readable;
import interfaces.Writeable;
import model.Order;

import java.io.*;

/**
 *
 */
public class OrderReaderWriter implements Readable<Order>, Writeable<Order> {

    /**
     *
     */
    public OrderReaderWriter() {
    }

    /**
     *
     * @param filename
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Override
    public Order read(String filename) throws IOException, ClassNotFoundException {
        return (Order) new ObjectInputStream(new FileInputStream(filename)).readObject();
    }

    /**
     *
     * @param item
     * @param filename
     * @throws IOException
     */
    @Override
    public void write(Order item, String filename) throws IOException {
        new ObjectOutputStream(new FileOutputStream(filename)).writeObject(item);
    }
}