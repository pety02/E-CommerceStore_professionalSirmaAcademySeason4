package utils;

import interfaces.Readable;
import interfaces.Writeable;
import model.PaymentProcessor;

import java.io.*;

/**
 *
 */
public class PaymentReaderWriter implements Readable<PaymentProcessor>, Writeable<PaymentProcessor> {

    /**
     *
     */
    public PaymentReaderWriter() {
    }

    /**
     *
     * @param filename
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Override
    public PaymentProcessor read(String filename) throws IOException, ClassNotFoundException {
        return (PaymentProcessor) new ObjectInputStream(new FileInputStream(filename)).readObject();
    }

    /**
     *
     * @param item
     * @param filename
     * @throws IOException
     */
    @Override
    public void write(PaymentProcessor item, String filename) throws IOException {
        new ObjectOutputStream(new FileOutputStream(filename)).writeObject(item);
    }
}
